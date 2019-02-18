# fiji-plugin-yaml-metadataCreator


# How to read in R-Studio
#install.packages("yaml")
library("yaml")
path = "filename.yaml"
input = paste0(readLines(path), collapse = "\n")
metadata = yaml.load(input)
