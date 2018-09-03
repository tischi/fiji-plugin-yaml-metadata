
import de.embl.cba.metadata.Metadata;
import de.embl.cba.metadata.MetadataCreator;
import ij.IJ;
import ij.ImageJ;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;


import java.io.FileWriter;
import java.util.*;

/**
 * Uses Bio-Formats to extract some basic standardized
 * (format-independent) metadata.
 */
public class BioFormatsMetadataExtractionTest
{

	public static void main(String[] args) throws Exception {

		ImageJ.main( args );

		String file = "/Volumes/cba/exchange/OeyvindOedegaard/yaml_project/20180627_LSM780M2_208_ibidi1_fcs_B_Posx96.lsm";

		final MetadataCreator metadataCreator = new MetadataCreator( file );

		final Metadata metadata = metadataCreator.getMetadata();

		final DumperOptions dumperOptions = new DumperOptions();
		dumperOptions.setDefaultFlowStyle( DumperOptions.FlowStyle.BLOCK );

		String outputPath = "/Volumes/cba/exchange/OeyvindOedegaard/yaml_project/test.yaml";
		Yaml yaml = new Yaml( dumperOptions );
		FileWriter writer = new FileWriter( outputPath );
		yaml.dump(metadata, writer);

		IJ.open( outputPath );



	}

}