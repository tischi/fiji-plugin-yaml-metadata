package de.embl.cba.metadata;

import ij.IJ;
import net.imagej.DatasetService;
import net.imagej.ops.OpService;
import org.scijava.app.StatusService;
import org.scijava.command.Command;
import org.scijava.log.LogService;
import org.scijava.plugin.Parameter;
import org.scijava.plugin.Plugin;
import org.scijava.ui.UIService;
import org.scijava.widget.FileWidget;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileWriter;

@Plugin(type = Command.class, menuPath = "Plugins>MetaData>Create YAML metadata" )
public class MetadataCommand implements Command
{

	public static final String AUTO = "Auto";
	public static final String BLOCK = "Block";
	public static final String FLOW = "Flow";

	@Parameter
	public UIService uiService;

	@Parameter
	public DatasetService datasetService;

	@Parameter
	public LogService logService;

	@Parameter
	public OpService opService;

	@Parameter
	public StatusService statusService;

	@Parameter( style = FileWidget.OPEN_STYLE )
	public File dataset;

	@Parameter( choices = { AUTO, BLOCK, FLOW } )
	public String yamlStyle = AUTO;

	public void run()
	{
		final MetadataCreator metadataCreator = new MetadataCreator( dataset.getAbsolutePath() );
		final MetaData metadata = metadataCreator.getMetadata();

		String outputPath = getOutputPath();

		createSnakeYamlFile( metadata, outputPath );

		IJ.open( outputPath );
	}

	public String getOutputPath()
	{
		final String absolutePath = dataset.getAbsolutePath();
		return absolutePath.substring(0, absolutePath.lastIndexOf('.')) + ".yaml";
	}

	public void createSnakeYamlFile(MetaData metadata, String outputPath )
	{
		try
		{
			Yaml yaml = new Yaml( getDumperOptions() );
			FileWriter writer = new FileWriter( outputPath );
			yaml.dump( metadata, writer );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
	}

	public DumperOptions getDumperOptions()
	{
		final DumperOptions dumperOptions = new DumperOptions();

		switch ( yamlStyle )
		{
			case AUTO: dumperOptions.setDefaultFlowStyle( DumperOptions.FlowStyle.AUTO ); break;
			case BLOCK: dumperOptions.setDefaultFlowStyle( DumperOptions.FlowStyle.BLOCK ); break;
			case FLOW: dumperOptions.setDefaultFlowStyle( DumperOptions.FlowStyle.FLOW ); break;
		}

		return dumperOptions;
	}

//	public void createJacksonYamlFile( MetaData metadata, String outputPath )
//	{
//
//		// Create an ObjectMapper mapper for YAML
//		//ObjectMapper mapper = new ObjectMapper(new YAMLFactory().enable( YAMLGenerator.Feature.MINIMIZE_QUOTES ) );
//
//		ObjectMapper mapper = new ObjectMapper(new YAMLFactory()
//				.enable( YAMLGenerator.Feature.MINIMIZE_QUOTES )
//				.enable( YAMLGenerator.Feature.LITERAL_BLOCK_STYLE ));
//
//		try
//		{
//			FileOutputStream fos = new FileOutputStream( outputPath );
//			SequenceWriter sw = mapper.writer().writeValues(fos);
//			sw.write( metadata );
//			fos.close();
//		}
//		catch ( Exception e )
//		{
//			e.printStackTrace();
//		}
//	}

}