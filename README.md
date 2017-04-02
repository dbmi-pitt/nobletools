# nobletools
Noble Tools Suite, is a set of Natural Language Processing (NLP) tools and Application Programming Interfaces (API) written in Java for interfacing with ontologies, auto coding text and extracting information from free test. The Noble Tools suite also includes a generic ontology API for interfacing with Web Ontology Language (OWL) files, OBO and BioPortal ontologies and a number of support utilities and methods useful forNLP (e.g. string normalization, ngram and stemming)

# Building the project

After cloning the project using git, use `mvn package` to build the project.

# Command Line Usage
You can invoke NobleCoderTool using command line as well as via UI. To run the NobleCoder from command line from the `target` directory after building the project as described above:

```
Usage: java -jar NobleCoderTool.jar -terminology <name> -input <dir> -output <dir> [options] 
```

The following options are available when running through command line:

	-terminology - terminology to use. All terminolgies are located in <user.home>/.terminologies directory.
	-input	- input directory containing a set of text files with (.txt) extension
	-output	- output directory where RESULT.csv output will be stored along with output HTML files
	-search	- search strategy: <best-match|precise-match|all-match|nonoverlap-match|partial-match|custom-match>
	-stripDigits	- don't try to match stand-alone digits
	-stripSmallWords	- don't try to match one letter words
	-stripCommonWords	- don't try to match most common English words
	-selectBestCandidates	- for each match only select the best candidate
	-semanticTypes	- <list of semantic types> only include matches from a given semantic types
	-sources	- <list of sources> only invlude matches from a given list of sources
	-slidingWindow	- <N> don't consider words that are N words apart to be part of the same concept
	-abbreviations	- <whitelist text file> a custom text file that suppresses all abbreviations except the ones in a list
	-ignoreUsedWords	- speed up search by not considering words that are already part of some concept
	-subsumptionMode	- subsume more general concepts if more specific concept is found
	-overlapMode	- overlapping concepts are allowed
	-contiguousMode	- matched terms must be contiguous in text
	-orderedMode	- matchd terms must use the same word order in text
	-partialMode	- match a term if more then 50% of its words are found in text
	-acronymExpansion	- if acronym is found in its expanded form, use its meaning to tag all other mentions of it
	-negationDetection	- invoke ConText algorithm to detect negated concepts and other modifiers
