/**
 *  Definicion de la clase Tokenizer.
 * Esta clase contiene los metodos necesarios para
 * tratar una cadena de texto en ingles y aplicarle
 * un modelo de los ya definidos para extraer los diferentes
 * tokens que contenga, y mostrarlos.
 * @author Hector92
 * @author Mindcrime
 * @version 1.1
 */
package org.fogbeam.example.opennlp;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;

import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;


public class TokenizerMain
{
  /**
  * Convierte el contenido de un fichero a una cadena.
  * @param path Directorio del fichero a convertir.
  * @param encoding Juego de caracteres utilizado en dicho fichero
  * @return Retorna la cadena ya transformada.
  */
	public static String readFile(String path, Charset encoding) throws Exception{
		  byte[] encoded = Files.readAllBytes(Paths.get(path));
		  return new String(encoded, encoding);
		}
		
   /**
  * Metodo principal de la clase Tokenizer. Recorre una cadena y extrae sus tokens.
  * La cadena se extrae de un fichero utilizando el metodo readFile
  * @see TokenizerMain#readFile(String, Charset)
  * @version 1.1
  * @param args Argumentos de entrada
  * @throws Exception Exception
  */
	public static void main( String[] args ) throws Exception
	{
		
		// the provided model
		// InputStream modelIn = new FileInputStream( "models/en-token.bin" );

		
		// the model we trained
		InputStream modelIn = new FileInputStream( "models/en-token.model" );
		
		try
		{
			TokenizerModel model = new TokenizerModel( modelIn );
		
			Tokenizer tokenizer = new TokenizerME(model);
			
				/* note what happens with the "three depending on which model you use */
			String[] tokens = tokenizer.tokenize
					(  readFile("inputfiles/inputtext.txt", StandardCharsets.UTF_8) );
			
			for( String token : tokens )
			{
				System.out.println( token );
			}
			
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
		System.out.println( "\n-----\ndone" );
	}
}
