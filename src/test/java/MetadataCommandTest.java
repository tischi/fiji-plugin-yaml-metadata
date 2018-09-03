import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import de.embl.cba.metadata.Metadata;
import de.embl.cba.metadata.MetadataCommand;
import net.imagej.ImageJ;

import java.io.File;

public class MetadataCommandTest
{

	public static void main(final String... args) throws Exception
	{
		final ImageJ ij = new ImageJ();
		ij.ui().showUI();

//		ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
//		final Metadata metadata = mapper.readValue( new File( "/Volumes/cba/exchange/OeyvindOedegaard/yaml_project/20180627_LSM780M2_208_ibidi1_fcs_D_Posx48_2.lsm.yaml" ), Metadata.class );

		// invoke the plugin
		ij.command().run( MetadataCommand.class, true );
	}

}
