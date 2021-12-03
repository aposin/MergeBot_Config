package org.opin.mergebot

import groovy.json.JsonSlurper

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
			final string maintainerFilePath = "NO_MAINTAINER_FILE" // if the maintainer concept should be switched of use "NO_MAINTAINER_FILE" otherwise provide path to Maintainer.json
			//Example implementation of a Status Check // do not forget to add the ErrorCode #001 in file: StatusCheckMessages.json: VALIDATION_HEAD/VALIDATION_DETAIL will be used as Status Check title and description.
			if(fileName.startsWith(" ")){
				result = result + "#001\n"
			} else {
				result = result + "SUCCESSFUL"
			}
			/*
			if(add further validations if you need them.){
				
				result=result + "#002\n"
			} 
			Sample implementation of maintainer concept:
			if(!maintainerFilePath.equals("NO_MAINTAINER_FILE") 
			&& (!input.reviewerList.isEmpty() || !input.reviewSubmitterList.isEmpty()) 
			&& !isReviewerMaintainer(input.reviewerList, input.reviewSubmitterList, maintainerFilePath)){
				result = result + "002\n"
			}
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

/* Example for using maintainers
private isReviewerMaintainer(List<String> reviewerList, List<String> reviewSubmitterList, String path) {
	def maintainer = new JsonSlurper().parse(new File(path), "UTF-8")
				List<String> maintainerList = maintainer.maintainer;
				reviewerListLowerCase = reviewerList.collect{it.toLowerCase()}
				reviewSubmitterListLowerCase = reviewSubmitterList.collect{it.toLowerCase()}
				reviewerListLowerCase.addAll(reviewSubmitterListLowerCase)
				return maintainerList.containsAll(reviewerListLowerCase)|| maintainerList.intersect(reviewSubmitterListLowerCase).size()>=1
} */
