package fr.clementababou.mergeanimations;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

public class Start {

	public static void main(String[] args) {
		Path workingPath = Paths.get(System.getProperty("user.dir"));
		List<String> directories = new ArrayList<String>();
		String exportPrefix = "exported_anim_";
		
		for (String arg : args) {
			String[] split = arg.split("=");
			System.out.println(Arrays.asList(split));
			
			if (split.length == 2) {
				if (split[0].equals("-prefix")) {
					if (split[1].length() > 0)
						exportPrefix = split[1];
				} else if (split[0].equals("-directories")) {
					if (split[1].length() > 0) {
						String[] inputDirectories = split[1].split(",");
						
						if (inputDirectories.length > 0) {
							for (String directory : inputDirectories) {
								if (directory.length() > 0) {
									directories.add(directory);
								}
							}
						}
					}
				}
			}
		}
		
		
		if (directories.size() == 0) {
			try {
				Files.list(workingPath).forEachOrdered((path) -> {
					if (Files.isDirectory(path) && !path.getFileName().toString().equals("export")) {
						System.out.println("Detected directory : " + path.getFileName().toString());
						directories.add(path.getFileName().toString());
					}
				});
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

		List<Path> animationsFiles = new ArrayList<Path>();
		
		for (String str : directories) {
			Path animationDir = Paths.get(workingPath.toAbsolutePath().toString(), str);
			
			if (Files.exists(animationDir, LinkOption.NOFOLLOW_LINKS)) {
				try {
					Files.list(animationDir).forEachOrdered((path) -> {
						animationsFiles.add(path);
					});
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				System.out.println("Directory : \"" + animationDir.toAbsolutePath().toString() + "\" doesn't exist");
			}
		}

		try {
			Path exportPath = Files.createDirectories(Paths.get(workingPath.toAbsolutePath().toString(), "export"));
			
			for(int i = 0; i < animationsFiles.size(); i++) {
				String paddedNumber = StringUtils.leftPad(String.valueOf(i), String.valueOf(animationsFiles.size() - 1).length(), '0');
				Path finalPath = Paths.get(exportPath.toAbsolutePath().toString(), exportPrefix + paddedNumber + "." + FilenameUtils.getExtension(animationsFiles.get(i).toString()));

				System.out.println("Copying file FROM : " + animationsFiles.get(i).toAbsolutePath().toString() + " TO : " + finalPath.toAbsolutePath().toString());
				FileUtils.copyFile(animationsFiles.get(i).toAbsolutePath().toFile(), finalPath.toAbsolutePath().toFile());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
