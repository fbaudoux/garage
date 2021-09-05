package fr.crew.garage.domain;

import java.util.ArrayList;
import java.util.List;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solution;
import org.chocosolver.solver.variables.IntVar;

public class algo {


   


    private static int[] engins = {1,2,3};
    private static    int[] personnes = {1,2,3,4,5,6};
    private static    int[][] competencesParEngins = { {1,2} , {3,4} , {5,6}};
    private static    int[][] competencesParPersonnel = { {1,2},{2,3} ,{3,4}, {4,5} , {5,6} , {6,1}};



    private static int[] personnelsCompetent(int competence){
        System.out.println("je cherche les personnels compétents sur la fonction " + competence); 
        List<Integer> personnelsCompetent = new ArrayList<>();
        int n = competencesParPersonnel.length;
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < competencesParPersonnel[i].length ; j++){
                if (competencesParPersonnel[i][j] == competence){
                    System.out.println("je trouve " + i); 
                    personnelsCompetent.add(i);
                }
            }
        }
            
        int[] array = personnelsCompetent.stream().mapToInt(i->i).toArray();

        for(int i = 0 ; i < array.length ; i++){

            System.out.println("je contiens " + array[i]);


        }


       
        return array;
    }


    public static void main(String argv[]) {
        int n = engins.length;
Model model = new Model(n + "engins problem");
int currentVar = 0;
IntVar[] vars = new IntVar[6];
for(int i = 0 ; i < n ; i++){
    System.out.println("L engin " + i + " possède " + competencesParEngins[i].length + " places");
    int nbSiege = competencesParEngins[i].length;
    for(int j = 0 ; j < nbSiege ; j++){
        vars[currentVar] = model.intVar("Q_"+i+"_"+j,personnelsCompetent(competencesParEngins[i][j]));
        currentVar++;
    }
}

model.intVar("v0", 9,45);

model.allDifferent(vars).post();

/*for(int i  = 0; i < n-1; i++){
    for(int j = i + 1; j < n; j++){
        model.arithm(vars[i], "!=",vars[j]).post();
        model.arithm(vars[i], "!=", vars[j], "-", j - i).post();
        model.arithm(vars[i], "!=", vars[j], "+", j - i).post();
    }
}*/
Solution solution = model.getSolver().findSolution();
if(solution != null){
    System.out.println(solution.toString());
}
        
    }
    
}
