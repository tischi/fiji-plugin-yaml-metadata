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

import java.io.File;

import static de.embl.cba.metadata.Yaml.createSnakeYamlFile;

@Plugin(type = Command.class, menuPath = "Plugins>MetaData>Create YAML metadata" )
public class MetadataCommand implements Command
{

	@Parameter( style = FileWidget.OPEN_STYLE )
	public File dataset;

	@Parameter( choices = { de.embl.cba.metadata.Yaml.AUTO, de.embl.cba.metadata.Yaml.BLOCK, de.embl.cba.metadata.Yaml.FLOW } )
	public String yamlStyle = de.embl.cba.metadata.Yaml.AUTO;

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