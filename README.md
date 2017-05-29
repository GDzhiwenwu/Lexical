# Lexical
#编译原理词法分析器Java代码
package com.data.Lex;
import java.io.*;  
import java.util.*;  
public class Lexer {  
	public static int m = 0; //记录列号
    public static int line = 1;     //记录行号  
    char peek = ' ';        //下一个读入字符 
    //保留关键字
    Hashtable<String, Word> words =   
        new Hashtable<String, Word>();  
    // 符号表 
    private Hashtable<Token, String> table =   
        new Hashtable<Token, String>();  
    /* token序列 */  
    private List<String> tokens =   
        new LinkedList<String> ();  
    
    // 读取文件变量   
    BufferedReader reader = null;   
    /* 保存当前是否读取到了文件的结尾  */  
    private Boolean isEnd = false;  
      
    /* 是否读取到文件的结尾 */  
    public Boolean getReaderState() {  
        return this.isEnd;  
    }  
    void reserve(Word w) {  
        words.put(w.lexme, w);  
    }  
      
//  构造函数中将关键字和类型添加到hashtable words中   
    public Lexer() {  
        /* 初始化读取文件变量 */  
        try {  
            reader = new BufferedReader(new FileReader("E:\\123.txt"));  
        }  
        catch(IOException e) {  
            System.out.print(e);  
        }  
          
          
        /* 关键字 */  
        this.reserve(new Word("if", Tag.IF));  
        this.reserve(new Word("then", Tag.THEN));  
        this.reserve(new Word("else", Tag.ELSE));  
        this.reserve(new Word("while", Tag.WHILE));  
        this.reserve(new Word("do", Tag.DO));  
          
        /* 类型 */  
        this.reserve(Word.True);  
        this.reserve(Word.False);  
        this.reserve(Type.Int);  
        this.reserve(Type.Char);  
        this.reserve(Type.Bool);  
        this.reserve(Type.Float);  
    }  
      
    public void readch() throws IOException {  
        /* 这里应该是使用的是 */  
        peek = (char)reader.read();  
        if((int)peek == 0xffff){  
            this.isEnd = true;  
        }  
        // peek = (char)System.in.read();  
    }  
      
    public Boolean readch(char ch) throws IOException {  
        readch();  
        if (this.peek != ch) {  
            return false;  
        }  
          
        this.peek = ' ';  
        return true;  
    }  
    @SuppressWarnings("unchecked")
	public Token scan() throws IOException {  
        // 消除空格 
        for( ; ; readch() ) {  
            if(peek == ' ' || peek == '\t')  
                continue;  
            else if (peek == '\n'){ 
                line = line + 1;  
                m = 0;
            }
            else  
                break;  
        }  
//        识别分界符:",",";","(",")","]","[";
        switch (peek) {
        case ',' :  
        	((List<String>) table).add("++");  
                m++;
                System.out.println(",	 "+"(2,,)	            "+"分界符	 "+"("+line+","+m+")");
                break ;
        case ';' :  
                m++;
                System.out.println(";	"+"(2，;)	            "+"分界符	 "+"("+line+","+m+")");
                break ;
            
        case '(' :  
        	((List<String>) table).add("(");  
            m++;
            System.out.println("(	"+"(2，( )	            "+"分界符	 "+"("+line+","+m+")");
            break ;
        case ')' :   
                m++;
                System.out.println(")	"+"(2,))	            "+"分界符	 "+"("+line+","+m+")");
                break ;
        case '[' :   
        	((List<String>) table).add("[");  
            m++;
            System.out.println("[	 "+"(2,[)	            "+"分界符	 "+"("+line+","+m+")");
            break ;
        case ']' :   
        	((List<String>) table).add("]");  
            m++;
            System.out.println(")	 "+"(2,])	            "+"分界符	 "+"("+line+","+m+")");
        }
//        识别"=","==",">=","<=","!=",">,"<"";
        switch (peek) {  
        case '=' :  
            if (readch('=')) {  
            	((List<String>) table).add("==");  
                m++;
                System.out.println("==	"+"(4,==)	            "+"关系运算符	 "+"("+line+","+m+")");
                return Word.eq;   
            }  
            else {  
                m++;
                System.out.println("=	"+"(4,=)	            "+"关系运算符	 "+"("+line+","+m+")");
                return new Token('=');  
            }  
        case '>' :  
            if (readch('=')) {  
            	((List<String>) table).add(">=");  
                m++;
                System.out.println(">=	"+"(4,>=)	            "+"关系运算符	 "+"("+line+","+m+")");
                return Word.ge;  
            }  
            else {  
            	((List<String>) table).add(">");  
                m++;
                System.out.println(">	 "+"(4,>)	            "+"关系运算符	 "+"("+line+","+m+")");
                return new Token('>');  
            }  
        case '<' :  
            if (readch('=')) {  
                m++;
                System.out.println("<=	"+"(4,<=)	            "+"关系运算符	 "+"("+line+","+m+")");
                return Word.le;  
            }  
            else {  
            	((List<String>) table).add("<");  
                m++;
                System.out.println("<	 "+"(4,<)	            "+"关系运算符	 "+"("+line+","+m+")");
                return new Token('<');  
            }  
        case '!' :  
            if (readch('=')) {  
            	((List<String>) table).add("!=");  
                m++;
                System.out.println("!=	 "+"(4,!=)	            "+"关系运算符	 "+"("+line+","+m+")");
                return Word.ne;  
            }  
            else {  
            	((List<String>) table).add("!");  
                m++;
                System.out.println("!	 "+"(4,!)	            "+"关系运算符	 "+"("+line+","+m+")");
                return new Token('!');  
            } 
            
        }  
        
        
//   识别"+","-","*","/","++","--"; 
        switch (peek) {
        case '+' :  
            if (readch('+')) {  
                m++;
                System.out.println("++	"+"(3,++)	            "+"算数运算符	 "+"("+line+","+m+")");
                return Word.eq;   
            }  
            else {  
            	((List<String>) table).add("+");  
                m++;
                System.out.println("+	 "+"(3,+)	            "+"算数运算符	 "+"("+line+","+m+")");
                return new Token('+');  
            }  
        case '*' :  
        	((List<String>) table).add("*");  
                m++;
                System.out.println("*	 "+"(3，*)	            "+"算数运算符	 "+"("+line+","+m+")");
                return Word.ge; 
            
        case '/' :  
        	((List<String>) table).add("*");  
            m++;
            System.out.println("*	 "+"(3，*)	            "+"算数运算符	 "+"("+line+","+m+")");
            return Word.ge;   
        case '-' :  
            if (readch('-')) {  
            	((List<String>) table).add("--");  
                m++;
                System.out.println("--	"+"(3,--)	            "+"算数运算符	 "+"("+line+","+m+")");
                return Word.eq;   
            }  
            else {  
            	((List<String>) table).add("-");  
                m++;
                System.out.println("-	 "+"(3,-)	            "+"算数运算符	 "+"("+line+","+m+")");
                return new Token('-');  
            }
            
        }
        
          
//	识别整数
        if(Character.isDigit(peek)) {  
            int value = 0;  
            do {  
                value = 10 * value + Character.digit(peek, 10);  
                readch();  
            } while (Character.isDigit(peek));  
              
            Num n = new Num(value);  
            tokens.add(n.toString());  
            m++;
            System.out.println(n+"	(5,"+n+")	            "+"常数	 "+"("+line+","+m+")");
            //table.put(n, "Num");  
            return n;  
        }  
//	关键字或者是标识符的识别 
        if(Character.isLetter(peek)) {  
            StringBuffer sb = new StringBuffer();  
              
            /* 首先得到整个的一个分割 */  
            do {  
                sb.append(peek);  
                readch();  
            } while (Character.isLetterOrDigit(peek));  
              
//       将识别的单词跟HashMap的关键字对比            
            String s = sb.toString();  
            Word w = (Word)words.get(s);  
              
            ///如果是关键字或者是类型的话，w不应该是空的 */  
            if(w != null) {  
                // table.put(w, "KeyWord or Type");  
                tokens.add(w.toString());  
                m++;
                System.out.println(w+"	(1,"+w+")	 "+"关键字		 "+"("+line+","+m+")");
                return w; /* 说明是关键字 或者是类型名 */  
            }  
              
            /* 否则就是一个标识符id */  
            w = new Word(s, Tag.ID);  
            tokens.add(w.toString());  
            m++;
            System.out.println(w+"	(6,"+w+")	            "+"标识符	 "+"("+line+","+m+")");
            table.put(w, "id");  
            words.put(s,  w);  
              
            return w;  
        }
        /* peek中的任意字符都被认为是词法单元返回 */  
        Token tok  = new Token(peek);  
        if ((int)peek != 0xffff )   
            tokens.add(tok.toString());  
        peek = ' ';  
          
        return tok;  
}  
}



















































/* 保存存储在table中的 */  
/*    public void saveSymbolsTable() throws IOException {  

	FileWriter writer = new FileWriter("E:\\1.txt");  
    writer.write("[符号]          [符号类型信息]\n");  
    writer.write("\r\n");  
     
    Enumeration<Token> e = table.keys(); 
    while( e.hasMoreElements() ){  
        Token token = (Token)e.nextElement();  
        String desc = table.get(token);  
          
   
        writer.write(token + "\t\t\t" + desc + "\r\n");  
//        System.out.println(token + "\t\t\t" + desc + "\r\n");输出标识符
    }  
          
    writer.flush();  
}   
public void saveTokens() throws IOException {  
    FileWriter writer = new FileWriter("E:\\2.txt");  
    writer.write("[符号]  \n");  
    writer.write("\r\n");  
      
    for(int i = 0; i < tokens.size(); ++i) {  
        String tok = (String)tokens.get(i);  
           
        writer.write(tok + "\r\n");  //把分析出来的符号存进文本
    }     
          
    writer.flush();  
}  
*/
