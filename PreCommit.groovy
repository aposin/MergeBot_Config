package org.opin.mergebot

import groovy.json.JsonSlurper
import groovy.transform.Field

import java.nio.ByteBuffer
import java.nio.charset.CharacterCodingException
import java.nio.charset.Charset
import java.nio.charset.CharsetDecoder
import java.nio.charset.StandardCharsets
import java.security.Key
import java.util.logging.Logger

import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

import org.apache.directory.api.ldap.model.entry.DefaultAttribute
import org.apache.directory.api.ldap.model.entry.Entry
import org.apache.directory.ldap.client.api.LdapConnection
import org.apache.directory.ldap.client.api.LdapNetworkConnection

@Field Logger logger =Logger.getLogger("PreCommit.class");
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

private boolean isUTF8Encoded(File file) {
	byte[] binaryContent = file.bytes
	CharsetDecoder decoder =
			StandardCharsets.UTF_8.newDecoder();
	try {
		decoder.decode(
				ByteBuffer.wrap(binaryContent));
	} catch (CharacterCodingException ex) {
		println "no utf"
		return false
	}
	return true
}
