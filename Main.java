# Lexical
#编译原理词法分析器Java代码
package com.data.Lex;
import java.io.*;  
  
public class Main  {  
    public static void main(String[] args) throws IOException {  
    	
    	
    	
//    	构造Lexer对象，以便使用getReaderState()跟scan()函数;
    	System.out.println("词法分析开始：");
        Lexer lexer = new Lexer();  
        System.out.println("单词	    "+"二元序列	"+"类型	"+"位置(行，列)");
        while (lexer.getReaderState() == false) {  //如果没有分析到代码的最后，则一直从左往右扫描；
            lexer.scan();  
        }   
        System.out.println("词法分析完成！");
          
    }
}  



/* 保存相关信息 */  
//lexer.saveTokens();  
//lexer.saveSymbolsTable(); 
