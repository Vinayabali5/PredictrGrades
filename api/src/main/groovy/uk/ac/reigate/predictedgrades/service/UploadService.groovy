package uk.ac.reigate.predictedgrades.service

import java.nio.file.Paths

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

import uk.ac.reigate.predictedgrades.exception.ApiException
import uk.ac.reigate.predictedgrades.exception.DataNotFoundException

@Service
class UploadService {
    
    @Value(value = '${upload-folder:uploads}')
    String uploadFolderPath
    
    private static final Logger LOGGER = LoggerFactory.getLogger(UploadService.class);

    /**
     * This method is used to store the uploaded files to the file system 
     * 
     * @param studentId The studentId to use for the storage path.
     * @param file The MultipartFile to store.
     */
    void storeFile(Integer studentId, MultipartFile file) {
        String path = "$uploadFolderPath/$studentId/SchoolReport"
        File uploadFolder = new File(path);
        //TODO: add exception handling
        if (!uploadFolder.exists()) {
            uploadFolder.mkdirs()
        }
		String actualFilename = Paths.get(file.getOriginalFilename()).getFileName()
		if (actualFilename.contains("/") || actualFilename.contains("\\") || actualFilename.contains(":")) {
			LOGGER.info("Actual Filename '$actualFilename' not in correct format")
			actualFilename = actualFilename.replace("\\", "/")
			actualFilename = actualFilename.substring(actualFilename.lastIndexOf("/"))
			LOGGER.info("Actual Filename - post parse: '$actualFilename'")
			//throw new ApiException("A problem occured with the upload")
    	}
		String filename = path + "/" + actualFilename
        LOGGER.info("Upload for student ID: $studentId, original file: $file.originalFilename, destination: $filename")
        File uploadFile = new File(filename)
        uploadFile.createNewFile()
        FileOutputStream fout = new FileOutputStream(uploadFile)
        fout.write(file.getBytes())
        fout.close()
        LOGGER.info("File successfully stored: $filename")
    }
    
	/**
	 * This method is used to retrieve a list of Files for the supplied studentId.
	 * 
	 * @param studentId The studentId to use for retrieving the list of Files.
	 * @return A list of File objects for the supplied studentId.
	 */
    List<File> listFiles(Integer studentId) {
        String path = "$uploadFolderPath/$studentId"
        File uploadFolder = new File(path);
        if (!uploadFolder.exists()) {
            throw new DataNotFoundException("There are no files uploaded for the student ID: $studentId")
        }
        if (uploadFolder.isDirectory()) {
            List<File> files = new ArrayList<File>()
            uploadFolder.listFiles().each {
                files.add(it)
            }
            return files  
        }
    }
}
