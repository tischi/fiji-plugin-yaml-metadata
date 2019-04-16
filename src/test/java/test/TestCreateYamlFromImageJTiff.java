package test;

import de.embl.cba.metadata.MetaData;
import de.embl.cba.metadata.MetadataCreator;
import de.embl.cba.metadata.Yaml;

import java.io.File;

import static de.embl.cba.metadata.Yaml.createSnakeYamlFile;

public class TestCreateYamlFromImageJTiff
{
	public static void main( String[] args )
	{

		final String path = TestCreateYamlFromImageJTiff.class.getResource( "../xy_8bit_pixelSize10nm.tif" ).getFile();
		final MetadataCreator metadataCreator = new MetadataCreator( path );
		final MetaData metadata = metadataCreator.getMetadata();
		String outputPath = path + ".yaml" ;

		createSnakeYamlFile( metadata, outputPath, Yaml.AUTO );

	}
}
