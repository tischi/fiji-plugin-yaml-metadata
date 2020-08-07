import de.embl.cba.metadata.MetaData as MetaData;
import de.embl.cba.metadata.MetadataCreator as MetadataCreator;
import ij.IJ as IJ;
import org.yaml.snakeyaml.DumperOptions as DumperOptions;
import org.yaml.snakeyaml.Yaml as Yaml;
import java.io.FileInputStream as FileInputStream;

outputPath = "/Volumes/cba/exchange/OeyvindOedegaard/yaml_project/test.yaml";
yaml = Yaml();
md = yaml.load( FileInputStream( "/Volumes/cba/exchange/OeyvindOedegaard/yaml_project/test.yaml" ) );
metaDict = md.metadata
print( metaDict[ MetadataCreator.IMAGE_DIMENSIONS ] );
