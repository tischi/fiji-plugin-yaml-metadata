import de.embl.cba.metadata.MetaData as MetaData;
import de.embl.cba.metadata.MetadataCreator as MetadataCreator;
import ij.IJ as IJ;
import org.yaml.snakeyaml.DumperOptions as DumperOptions;
import org.yaml.snakeyaml.Yaml as Yaml;
import java.io.FileWriter as FileWriter;

file = "/Volumes/cba/exchange/OeyvindOedegaard/yaml_project/01_TestFiles/20180627_LSM780M2_208_ibidi1_fcs_B_Posx96.lsm";
metadataCreator = MetadataCreator( file );
metadata = metadataCreator.getMetadata();

dumperOptions = DumperOptions();
dumperOptions.setDefaultFlowStyle( DumperOptions.FlowStyle.BLOCK );

outputPath = "/Volumes/cba/exchange/OeyvindOedegaard/yaml_project/test.yaml";
yaml = Yaml( dumperOptions );
writer = FileWriter( outputPath );
yaml.dump(metadata, writer);
writer.flush();
writer.close();

IJ.open( outputPath );
