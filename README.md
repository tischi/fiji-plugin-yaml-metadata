# fiji-plugin-yaml-metadataCreator

## How to use with Jython

[yaml from image](https://github.com/tischi/fiji-plugin-yaml-metadata/blob/master/src/test/resources/yaml_from_image.py)

## How to read in R-Studio

```
#install.packages("yaml")
library("yaml")
path = "filename.yaml"
input = paste0(readLines(path), collapse = "\n")
metadata = yaml.load(input)
```
