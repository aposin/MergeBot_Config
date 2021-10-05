package org.opin.mergebot

import groovy.json.JsonSlurper
@Grapes(
    @Grab(group='org.codehaus.groovy', module='groovy-json', version='3.0.9')
)

String path=args[0]
def input = new JsonSlurper().parse(new File(path), "UTF-8")

return process(input)
String process(def input){
	String result =""
	try{
		final String initialDeveloper = input.initialDeveloper
		StringBuffer resultMessage=new StringBuffer();

		input.filesToMerge.each {file ->
			String fileName = file.fileName.toLowerCase()
			String plugin = file.pluginName
			//Example implementation of a Status Check // do not forget to add the ErrorCode #001 in file: StatusCheckMessages.json: VALIDATION_HEAD/VALIDATION_DETAIL will be used as Status Check title and description.
			if(fileName.startsWith(" ")){
				result = result + "#001\n"
			} else {
				result = result + "SUCCESSFUL"
			}
			/*
			if(add further validations if oyu need them.){
				
				result=result + "#002\n"
			} 
			.
			.
			.
			*/	
		}

		println resultMessage
	}catch(Exception e){
		//system out is the output the MergeBot receives - therefore it is ok - Exception is written into log-File of MergeBot
		//logger.info(e.getMessage() + "\n" + e.getStackTrace())
		println e.getMessage() + "\n" + e.getStackTrace()
	}
	return result
}
