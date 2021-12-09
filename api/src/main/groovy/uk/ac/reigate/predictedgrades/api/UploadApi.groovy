package uk.ac.reigate.predictedgrades.api

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

import uk.ac.reigate.predictedgrades.service.UploadService

@RestController
class UploadApi {

    class UploadFormDataDto {
        Integer studentId
        MultipartFile file
    }
        
    @Autowired
    UploadService uploadService
    
    @CrossOrigin("*")
	@PostMapping(value = "/api/upload")
    def uploadFile(@RequestParam("studentId") Integer studentId, @RequestPart("file") MultipartFile file) {
		try {
			uploadService.storeFile(studentId, file)
		} catch (Exception e) {
			e.printStackTrace()
		}
    }
 
    @CrossOrigin("*")
	@PostMapping(value = "/api/upload/{studentId}")
    def uploadFileByPath(@PathVariable("studentId") Integer studentId, @RequestPart("file") MultipartFile file) {
		try {
			uploadService.storeFile(studentId, file)
		} catch (Exception e) {
			e.printStackTrace()
		}
    }
 
    @GetMapping(value = "/api/files/{studentId}")
    def listUploadedFiles(@PathVariable Integer studentId) {
        List<File> files = uploadService.listFiles(studentId)
        ArrayList<String> list = new ArrayList<String>()
        Map<String, File> map= new HashMap<String, File>()
        files.each { File it ->
            list.add(it.name)
            map.put(it.name, it)
        }
        return new ResponseEntity(list, HttpStatus.OK)
    }
       
}
