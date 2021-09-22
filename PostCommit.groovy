package org.opin.mergebot

import groovy.json.JsonSlurper
import groovy.transform.Field
import java.util.logging.Logger

@Field def configuration = new GroovyScriptEngine('.').with{loadScriptByName(args[1])}
this.metaClass.mixin configuration

@Field Logger logger =Logger.getLogger("PostCommit.class");
String path=args[0]
def input = new JsonSlurper().parse(new File(path), "UTF-8")
return process(input)


int process(def input){
	int result=0
	def baseUrl
	try{
		if(!input.reviewerList.isEmpty()){
			String reviewer = input.reviewerList.get(0)
			String pullRequestUrl = input.pullRequestUrl
			//TODO create any post actions on pull requests
		}
	}catch(Exception e){
		//system out is the output the MergeBot receives - therefore it is ok - Exception is written into log-File of MergeBot
		println (e.getMessage() + "\n" + e.getStackTrace())
	}
	return result
}
