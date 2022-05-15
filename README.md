# Merge Animations
Batch rename files to fit "[prefix]_[image index].[extension]"

This is a micro-project

# Release
[Release page](https://github.com/Klemms/MergeAnimations/releases/tag/1.0.0) for 1.0.0

## Usage
Put the JAR file in a folder, put your animation files in subfolders. Exported files will follow original folders and files alphabetical order.

Can be used without any argument, in this case the exported files will be prefixed with the default name ```exported_anim_``` and all directories next to the JAR file will be used except for the directory called ```export```.

Argument ```-prefix=[prefix]``` will change the default prefix

Argument ```-directories=[directory1],[directory2],[...]``` will make the program use these directories instead of all directories next to the JAR file

## Building

Manually add Apache Commons Lang and Apache Commons IO as dependencies, this is small enough project that it doesnt need Maven.

```bash
pip install foobar
```
## License
Uses [Apache Commons Lang](https://commons.apache.org/proper/commons-lang/) and [Apache Commons IO](https://commons.apache.org/proper/commons-io/) ([https://www.apache.org/licenses/](https://www.apache.org/licenses/))