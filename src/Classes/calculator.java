/*
--------------------------------------------------------------------------------------


*main calculator class*
just make an instance of that and give it a string and call solver method
there will be some feature update needing for this class and i write this 
code in a way that your(and my) hands can be open for next updating and 
adding features to it.


---------------------------------------------------------------------------------------
*/
package Classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


public class calculator {
    
    
    /**
     * enumeration for different Operations that we want to detect
     * in command String, one who have nonReg flag use non-regExp based-
     * recognition for operator and one who have reg based on regExp
     * 
     */
    public enum Operation{
        // basic Operations
        ADD("+"),
        SUBTRACT("-"),
        MULTIPLY("*"),
        DIVITION("/"),
        // two entry Operations
        POWER("^"),
        MAX("max",true),
        MIN("min",true),
        ATAN2("atan2",true),
        // one entry Operations

            // trigonometry
        Cosine_d("cos_d",true),
        Cosine_r("cos_r",true),
        aCosine("acos",true),
        cosh("cosh",true),
        Sinus_d("sin_d",true),
        Sinus_r("sin_r",true),
        aSinus("asin",true),
        sinh("sinh",true),
        tan_d("tan_d",true),
        tan_r("tan_r",true),
        atan("atan",true),
        tanh("tanh",true),
            // logarithmic
        Logarithm2("log_2",true),
        Logarithm10("log_10",true),
            // power
        exp("exp",true),
        sqrt("sqrt",true),
        cbrt("cbrt",true),
        
            // converters
        toDegree("toDegree",true),
        toRadian("toRadian",true),
        round("round",true),
        floor("floor",true),
        abs("abs",true),
        ceil("ceil",true),
            
        
            // probablity
        random("random",true),
        
        //splitors
            //paranteses
        OpenParantes("(",true),
        CloseParantes(")",true),
        Conditions("|"),
        
        // equality
        EQUALITY("=");
        
        String reg;
        String op;
        String contain;
        private Operation(String reg){
            this.op=reg;
            this.reg="\\"+reg;
        }
        private Operation(String reg,boolean nonReg){
            if(nonReg==true){
                this.op=reg;
                this.reg="("+reg+")+";
                this.contain=reg;
                if(reg.equals(")"))
                {this.reg=null;this.contain=")";}
                if(reg.equals("("))
                {this.reg=null;this.contain="(";}
            }
        }
    }
    
    
    /**
     * main command String
     * 
     */
    private String mainString;
    /**
     * 
     */
    private ArrayList<String> parsed;
    
    
    
    /*
    THE CONSTRUCTOR
    */
    public calculator(String instructions){
        this.mainString=instructions.trim();
    }
    /**
     * method for execute solving the command math string 
     * @return 
     * double -> return value from solve the command
     */
    /**
     * make an ArrayList of all different Operation and Operands in command
     * and parse them and save them to this.parsed ArrayList property
     * @return 
     */
    public ArrayList<String> parseMainString(){
        ArrayList<String> str=new ArrayList();
        str.add(mainString.trim());
        Operation[] ops=Arrays.asList(Operation.values()).toArray(new Operation[0]);
        this.parsed=parseOperation(str,ops);
        return this.parsed;
    }
    /*
    solver that initalize commands and run calculator with proper input 
    */
    public double solver(ArrayList<String> result,double x,double y){
        ArrayList<String> newRes=new ArrayList();
        newRes.addAll(result);
        for(int i=0;i<newRes.size();i++){
            String word=newRes.get(i);
            if(word.equals(""))
                newRes.remove(i);
            if(word.equals("x")){
                newRes.remove(i);
                newRes.add(i,String.format("%.10f",x));
            }
            if(word.equals("y")){
                newRes.remove(i);
                newRes.add(i,String.format("%.10f",y));
            }
        }
        return calculator(newRes);
    }
    /**
     * 
     * main function that calculate parsed array answer
     * this method has some steps
     * step1: operator () handling
     * 
     * in this step all instruction between '( )' will be evaluated,and if 
     *  it has any nested '( ( ) )' itself,the method will re-invoke itself
     *  and evaluate that and so on..
     * 
     * step2: operators like sin,cos,tan,asin,acos,log_10,log_2,floor,round,..
     * 
     * step3: operators like + - / * 
     * 
     * 
     * @param leftSide
     * the parsed array from input string
     * @return 
     * double answer of calculation
     */
    private double calculator(ArrayList<String> leftSide){
        // this if for command like -> 10.0
        if(leftSide.size()==1)
        {return Double.parseDouble(leftSide.get(0));}
        // this if for 
        if(leftSide.size()==3 && leftSide.get(0).equals("(") && leftSide.get(2).equals(")"))
            {return (double)Double.parseDouble(leftSide.get(1));}
        double result=0;
// operator ( ) handling
        for(int i=0;i<leftSide.size();i++){
            String item=leftSide.get(i);
            int num_of_words=1;
            if(item.equals(Operation.OpenParantes.op)){
                ArrayList<String> temp=new ArrayList();
                int lastIndex=i;
                leftSide.remove(i);
                float newRes=0;
                int number_of_inner_paranteses=0;
                while(true){
                    String newWord=leftSide.get(i);
                    if(newWord.equals(Operation.CloseParantes.op))
                        {
                            num_of_words++;
                            if(number_of_inner_paranteses!=0){  
                                number_of_inner_paranteses--;
                                temp.add(newWord);
                                leftSide.remove(i);
                            }else{
                                leftSide.remove(i);
                                newRes+=calculator(temp);
                                if(num_of_words==3)
                                    {
                                        leftSide.add(lastIndex,String.valueOf(temp.get(0)));
                                    }   
                                else
                                    leftSide.add(lastIndex,String.valueOf(newRes));
                                break;
                            }
                        }
                    else
                        {
                            num_of_words++;
                            if(newWord.equals(Operation.OpenParantes.op)){
                                    number_of_inner_paranteses++;
                            }
                            temp.add(newWord);
                            leftSide.remove(i);
                        }
                }
            }
        }
         if(leftSide.size()==1)
        {return Double.parseDouble(leftSide.get(0));}
        if(leftSide.size()==3 && leftSide.get(0).equals("(") && leftSide.get(2).equals(")"))
            {return (double)Double.parseDouble(leftSide.get(1));}       
        
// operator sin,cos,log,tan,cot,..      
        result+=solve_1EF(leftSide);
        
// the array is Sequentially
        Operation[] twoEntryOps={
            Operation.POWER,
            Operation.DIVITION,
            Operation.MULTIPLY,
            Operation.SUBTRACT,
            Operation.ADD,
        };
        for(Operation op:twoEntryOps){
            result+=solve_2EF(leftSide, op);
        }
        return result;
    }
    /*
    this method solve all 2side functions in given arraylist and the recipe of execution
    what kind of function related to operation is important and you should -
    notice that .as you can see i declare an array in that function that i use and 
    there is a recipe in that!
        Operation[] twoEntryOps={
            Operation.POWER,
            Operation.DIVITION,
            Operation.MULTIPLY,
            Operation.SUBTRACT,
            Operation.ADD,
        };
    */
    private double solve_2EF(ArrayList<String> leftSide,Operation op){
        double result=0;
        for(int i=0;i<leftSide.size();i++){
            String item=leftSide.get(i);
            if(item.equals(op.op)){
                double newRes=0;
                int lastIndex=0;
                double a1=0;
                double a2=Double.parseDouble(leftSide.get(i+1));
                try{
                    a1=Double.parseDouble(leftSide.get(i-1));
                    leftSide.remove(i-1);
                    leftSide.remove(i-1);
                    leftSide.remove(i-1);
                    lastIndex=i-1;
                }
                catch(Exception exp){
                    // accures when like '-1' => - , 1
                    leftSide.remove(i);
                    leftSide.remove(i);
                    lastIndex=i;
                }
                if(op==Operation.POWER)
                    newRes+=Math.pow(a1, a2);
                if(op==Operation.DIVITION)
                    newRes+=(a1/a2);            
                if(op==Operation.MULTIPLY)
                    newRes+=(a1*a2);
                if(op==Operation.ADD)
                    newRes+=(a1+a2);
                if(op==Operation.SUBTRACT)
                    newRes+=(a1-a2); 
                leftSide.add(lastIndex,String.valueOf(newRes));
                if(leftSide.size()==1)
                    {result+=newRes;break;}
                else
                    i=0;
            }
        }
        return result;

    }
    /*
    solve function for one entry function like abs,floor,sin,cos,log,log10,..
    */
    private double solve_1EF(ArrayList<String> leftSide){
        double result=0;
        Operation op=Operation.Sinus_r;
        List<Operation> ops=Arrays.asList(Operation.values());
        boolean flag=false;
        for(int i=leftSide.size()-1;i>=0;i--){
            String item=leftSide.get(i);
            try{
                for(Operation o:ops){
                    if(item.equals(o.contain) && !"(".equals(item) && !")".equals(item)){
                        op=o;
                        flag=true;
                        break;
                    }
                }
            }
            catch(Exception exp){
                flag=false;
            }
            if(flag==true){
                flag=false; 
                double newRes=0;
                int lastIndex=i;
                double arg=Double.parseDouble(leftSide.get(i+1));
                
                
                if(op==Operation.Sinus_r)
                    newRes+=Math.sin(arg);
                if(op==Operation.Sinus_d)
                    newRes+=Math.sin(Math.toRadians(arg));
                if(op==Operation.aSinus)
                    newRes+=Math.asin(arg);
                if(op==Operation.sinh)
                    newRes+=Math.sinh(arg);
                if(op==Operation.Cosine_r)
                    newRes+=Math.cos(arg);
                if(op==Operation.Cosine_d)
                    newRes+=Math.cos(Math.toRadians(arg));        
                if(op==Operation.aCosine)
                    newRes+=Math.acos(arg); 
                if(op==Operation.cosh)
                    newRes+=Math.cosh(arg);
                if(op==Operation.tan_r)
                    newRes+=Math.tan(arg);
                if(op==Operation.tan_d)
                    newRes+=Math.tan(Math.toRadians(arg));
                if(op==Operation.atan)
                    newRes+=Math.atan(arg); 
                if(op==Operation.tanh)
                    newRes+=Math.tanh(arg);
                
                if(op==Operation.Logarithm2)
                    newRes+=Math.log(arg);
                if(op==Operation.Logarithm10)
                    newRes+=Math.log10(arg);
                
                if(op==Operation.exp)
                    newRes+=Math.exp(arg);
                if(op==Operation.abs)
                    newRes+=Math.abs(arg);
                if(op==Operation.sqrt)
                    newRes+=Math.sqrt(arg);
                if(op==Operation.ceil)
                    newRes+=Math.ceil(arg);        
                if(op==Operation.cbrt)
                    newRes+=Math.cbrt(arg); 
                if(op==Operation.floor)
                    newRes+=Math.floor(arg);
                if(op==Operation.round)
                    newRes+=Math.round(arg); 
                if(op==Operation.toDegree)
                    newRes+=Math.toDegrees(arg); 
                if(op==Operation.toRadian)
                    newRes+=Math.toRadians(arg);
                
                leftSide.remove(i);
                leftSide.remove(i);
                leftSide.add(lastIndex,String.valueOf(newRes));
                
                if(leftSide.size()==1)
                    {result+=newRes;break;}
                else
                    i=leftSide.size()-1;
            }
        }
        
        return result;
    }
    /*
    parse the command string to proper operation and operator list that 
    prepared based on ops Ooperations
    i know that this method's code is dirty !! but im to late(-.-) 
    this code have a relation with Operation enum and using regExp and 
    String indexing.
    */
    private ArrayList<String> parseOperation(ArrayList<String> str,Operation[] ops){
        ArrayList<String> result;
        result=(ArrayList<String>) str.clone();
        for(Operation op:ops){
            Iterator<String> it=result.iterator();
            ArrayList<String> temp = new ArrayList();
            while(it.hasNext()){
                if(op.reg!=null){
                    String next=it.next();
                    String[] word=next.split(op.reg);
                    if(word.length==1 && (word[0] == null ? op.op != null : !word[0].equals(op.op))){
                    temp.add(word[0]);}
                    if(word.length>1){
                    for(int i = 0;i<word.length-1;i++){temp.add(word[i]);temp.add(op.op);}temp.add(word[word.length-1]);
                    }
                }else if(op.reg==null){
                    String word=it.next();
                    int index=word.indexOf(op.contain);
                    if(index==-1){temp.add(word);}
                    while(index!=-1){
                        if(")".equals(op.contain)){
                            temp.add(word.subSequence(0,index).toString());
                            temp.add(")");
                            word=word.subSequence(index+op.contain.length(),word.length()).toString();
                            index=word.indexOf(op.contain);                            
                            if(index==-1 && !"".equals(word)){
                                    temp.add(word);
                            }}
                            if("(".equals(op.contain)){
                            temp.add(word.subSequence(0,index).toString());
                            temp.add("(");
                            word=word.subSequence(index+op.contain.length(),word.length()).toString();
                            index=word.indexOf(op.contain);                            
                            if(index==-1 && !"".equals(word)){
                                    temp.add(word);
                            }
                            }
                    }
                }
                
                }
            result.clear();
            result=temp;
        }
            return result;
    }
    
}






/*
--------------------------------------------------------------------------------------
MIT licence@KBase -> https://github.com/behzadhasanpor
---------------------------------------------------------------------------------------
*/







