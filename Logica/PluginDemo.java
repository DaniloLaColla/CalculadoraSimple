package Logica;


import java.io.File;
import java.util.*;

public class PluginDemo {

	// the directory where we keep the plugin classes
	String pluginsDir;

	// a list where we keep an initialized object of each plugin class
	List plugins;

	public static void main (String args[]) {
		PluginDemo demo = new PluginDemo(args);
		demo.getPlugins();
		demo.runPlugins();
	}

	PluginDemo (String args[]) {
		if (args.length > 0)
			pluginsDir = args[0];
		else
			pluginsDir = "plugins";

		plugins = new ArrayList();

	}

	protected void getPlugins() {
		File dir = new File(System.getProperty("user.dir") + File.separator + pluginsDir);
		ClassLoader cl = new PluginClassLoader(dir);
		if (dir.exists() && dir.isDirectory()) {
			// we'll only load classes directly in this directory -
			// no subdirectories, and no classes in packages are recognized
			String[] files = dir.list();
			for (int i=0; i<files.length; i++) {
				try {
					// only consider files ending in ".class"
					if (! files[i].endsWith(".class"))
						continue;

					Class c = cl.loadClass(files[i].substring(0, files[i].indexOf(".")));
					Class[] intf = c.getInterfaces();
					for (int j=0; j<intf.length; j++) {
						if (intf[j].getName().equals("PluginFunction")) {
							// the following line assumes that PluginFunction has a no-argument constructor
							PluginFunction pf = (PluginFunction) c.newInstance();
							plugins.add(pf);
							continue;
						}
					}
				} catch (Exception ex) {
					System.err.println("File " + files[i] + " does not contain a valid PluginFunction class.");
				}
			}
		}
	}

	protected void runPlugins() {
		int count = 1;
		Iterator iter = plugins.iterator();
		while (iter.hasNext()) {
			PluginFunction pf = (PluginFunction) iter.next();
			try {
				pf.setParameter(count);
				System.out.print(pf.getPluginName());
				System.out.print(" ( "+count+" ) = ");
				if (pf.hasError()) {
					System.out.println("there was an error during plugin initialization");
					continue;
				}
				int result = pf.getResult();
				if (pf.hasError())
					System.out.println("there was an error during plugin execution");
				else
					System.out.println(result);
				count++;
			} catch (SecurityException secEx) {
				System.err.println("plugin '"+pf.getClass().getName()+"' tried to do something illegal");
			}
		}
	}
}

