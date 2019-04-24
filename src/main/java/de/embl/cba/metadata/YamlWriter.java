package de.embl.cba.metadata;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.FileWriter;

public abstract class YamlWriter
{
	public static final String AUTO = "Auto";
	public static final String BLOCK = "Block";
	public static final String FLOW = "Flow";

	public static void createSnakeYamlFile( MetaData metadata, String outputPath, String yamlStyle )
	{
		try
		{
			Yaml yaml = new Yaml( getDumperOptions( yamlStyle ) );
			FileWriter writer = new FileWriter( outputPath );
			yaml.dump( metadata, writer );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
	}

	public static DumperOptions getDumperOptions( String yamlStyle )
	{
		final DumperOptions dumperOptions = new DumperOptions();

		switch ( yamlStyle )
		{
			case AUTO: dumperOptions.setDefaultFlowStyle( DumperOptions.FlowStyle.AUTO ); break;
			case BLOCK: dumperOptions.setDefaultFlowStyle( DumperOptions.FlowStyle.BLOCK ); break;
			case FLOW: dumperOptions.setDefaultFlowStyle( DumperOptions.FlowStyle.FLOW ); break;
		}

		dumperOptions.setDefaultScalarStyle( DumperOptions.ScalarStyle.SINGLE_QUOTED );

		return dumperOptions;
	}
}
