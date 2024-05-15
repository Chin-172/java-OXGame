import java.util.Objects;

public class OXManager {

    int round = 1;
    String winner = "";
    int playerMove = 1; // initial start at player 1 ("O")
    Box[] allData;

    OXManager(){
        allData = new Box[9];
        for(int i = 0 ; i < 9 ; i++){
           allData[i] = new Box();
           allData[i].setData(i,"");
        }
    }

    public void printBox(int i){
        System.out.println(allData[i].getData());
    }

    public void setData(int selectedIndx){
        if(playerMove == 1){
            System.out.println(selectedIndx+" set as O");
            allData[selectedIndx].setData(selectedIndx,"O");
        }else{
            System.out.println(selectedIndx+" set as X");
            allData[selectedIndx].setData(selectedIndx,"X");
        }
    }

    public boolean isSet(int i){
        return !allData[i].getData().isEmpty();
    }

    public String getPlayer(){
        if(round % 2 == 1){
            return "O";
        }else{
            return "X";
        }
    }

    public void roundDone(){
        round++;
        if(round % 2 == 1){
            playerMove = 1;
        }else{
            playerMove = 2;
        }
    }

    public String getWinner(){
        if(isWin() == 1){
            return winner;
        }
        return "";
    }


    private int isWin(){
        for(int i = 0 ; i < 9 ; i++){
            winner = allData[i].getData();
            String checker = allData[i].getData();

            if(winner.isEmpty()){
                continue;
            }

            if(i== 0 || i==3 || i==6){
                // Row check
                int flag = 0;
                for(int r = i; r<=i+2; r++){
                    checker = allData[r].getData();
                    if(!Objects.equals(winner, checker)){
                        flag = 1;
                    }
                }

                if(Objects.equals(winner, checker) && flag == 0){
                    return 1;
                }
            }

            if(i == 0 || i == 1 || i == 2){
                // Column Check
                int flag = 0;
                for(int r = i; r<=i+6; r=r+3){
                    checker = allData[r].getData();
                    if(!Objects.equals(winner, checker)){
                        flag = 1;
                    }
                }
                if(Objects.equals(winner, checker) && flag == 0){
                    return 1;
                }
            }

            if(i == 0 || i == 2) {
                // Slash Check -- 0.4.8 || 2.4.6
                int flag = 0;
                int end = 8-i;
                int jump = (end - i)/2;
                for(int r = i; r<=end; r=r+jump){
                    checker = allData[r].getData();
                    if(!Objects.equals(winner, checker)){
                        flag = 1;
                    }
                }
                if(Objects.equals(winner, checker) && flag == 0){
                    return 1;
                }

            }
        }
        return  0;
    }
}
