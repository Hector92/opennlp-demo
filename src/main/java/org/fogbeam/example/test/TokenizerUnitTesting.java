package org.fogbeam.example.opennlp.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import org.junit.Test;

import junit.framework.TestCase;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;


public class TokenizerUnitTesting extends TestCase {
	
		public static String readFile(String path, Charset encoding) throws Exception{
		  byte[] encoded = Files.readAllBytes(Paths.get(path));
		  return new String(encoded, encoding);
		}
	
@Test
	public void checkCorrectTokens() throws Exception{
		String[] tokensCorrectos = {		
"It",
"is",
"a",
"largely",
"terrestrial",
"bird",
"the",
"size",
"of",
"a",
"small",
"domestic",
"chicken",
",",
"with",
"mainly",
"brown",
"upperparts",
",",
"finely",
"banded",
"black",
"and",
"white",
"underparts",
",",
"a",
"white",
"eyebrow",
",",
"chestnut",
"band",
"running",
"from",
"the",
"bill",
"round",
"the",
"nape",
",",
"with",
"a",
"buff",
"band",
"on",
"the",
"breast",
"."	
		};


		InputStream modelIn = new FileInputStream( "models/en-token.model" );
			try {
				TokenizerModel model = new TokenizerModel(modelIn);
				Tokenizer tokenizer = new TokenizerME(model);
				String[] tokens = tokenizer.tokenize
					(  readFile("inputfiles/inputtext.txt", StandardCharsets.UTF_8) );

				assertEquals(tokens.length, tokensCorrectos.length);
				for(int i = 0; i < tokens.length; i++)
					assertTrue(tokens[i].equals(tokensCorrectos[i]));

			}
		catch( IOException e )
		{
			e.printStackTrace();
		}
		finally
		{
			if( modelIn != null )
			{
				try
				{
					modelIn.close();
				}
				catch( IOException e )
				{
				}
			}
		}
	}
}
