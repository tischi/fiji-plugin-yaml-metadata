package de.embl.cba.metadata;

import ij.IJ;
import org.scijava.command.Command;
import org.scijava.plugin.Parameter;
import org.scijava.plugin.Plugin;
import org.scijava.widget.FileWidget;

import java.io.File;

import static de.embl.cba.metadata.YamlWriter.createSnakeYamlFile;

@Plugin(type = Command.class, menuPath = "Plugins>MetaData>Create YAML metadata" )
public class MetadataCommand implements Command
{

	@Parameter( style = FileWidget.OPEN_STYLE )
	public File dataset;

	@Parameter( choices = { YamlWriter.AUTO, YamlWriter.BLOCK, YamlWriter.FLOW } )
	public String yamlStyle = YamlWriter.AUTO;

	public void run()
	{
		final MetadataCreator metadataCreator = new MetadataCreator( dataset.getAbsolutePath() );

		final MetaData metadata = metadataCreator.getMetadata();
		String outputPath = getOutputPath();
		createSnakeYamlFile( metadata, outputPath, yamlStyle );

		IJ.open( outputPath );
	}

	public String getOutputPath()
	{
		final String absolutePath = dataset.getAbsolutePath();
		return absolutePath.substring(0, absolutePath.lastIndexOf('.')) + ".yaml";
	}

}