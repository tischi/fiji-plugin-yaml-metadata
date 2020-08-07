# fiji-plugin-yaml-metadataCreator

## How to use with Jython

- [create yaml from image](https://github.com/tischi/fiji-plugin-yaml-metadata/blob/master/src/test/resources/yaml_from_image.py)
- [read yaml into a dict](https://github.com/tischi/fiji-plugin-yaml-metadata/blob/master/src/test/resources/read_yaml.py)
  - [metadata fields](https://github.com/tischi/fiji-plugin-yaml-metadata/blob/master/src/main/java/de/embl/cba/metadata/MetadataCreator.java#L19)

## How to read in R-Studio

```
#install.packages("yaml")
library("yaml")
path = "filename.yaml"
input = paste0(readLines(path), collapse = "\n")
metadata = yaml.load(input)
```
