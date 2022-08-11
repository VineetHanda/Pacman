package levelone;

import java.awt.Graphics2D;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class EnemyPacmanGreen extends JPanel {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    int i;
    ImageIcon enemypacman1;
    static int enemyXCoordinate, enemyYCoordinate;
    static PlayPanel.GAMEZOON enemypacgameZoon;
    boolean movingRight, movingLeft;
    static boolean moveUp;
    static boolean moveDown;
    static Random random;
    //boolean repeat[];

    public void load() {
        enemyXCoordinate = 50;//
        enemyYCoordinate = 3;//
        movingRight = true;//
        movingLeft = false;//
        moveUp = false;
        moveDown = false;
		/*repeat=new boolean[9];
		for(int i=0;i<=8;i++)
			repeat[i]=false;*/
        enemypacgameZoon = PlayPanel.GAMEZOON.zoon9;//
        random = new Random();
        try {
            enemypacman1 = new ImageIcon(getClass().getResource("/images/Enemy Pac/greenrightpac.gif"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //System.out.println(enemypacman1);
    }//load()

    public void updateEnemyPacman() {
        switch (enemypacgameZoon) {
            case zoon1:
                if (enemyXCoordinate <= 270 && enemyXCoordinate >= 249) {
                    //if(repeat[0])
                    moveUp();
                }
                break;

            case zoon2:
                if (enemyXCoordinate < 10) {
                    //if(repeat[1])
                    moveUp();
                }
                if (enemyXCoordinate <= 270 && enemyXCoordinate >= 249) {
                    //if(repeat[1])
                    moveDown();
                }
                break;

            case zoon3:
                if ((enemyXCoordinate >= 188 && enemyXCoordinate <= 205) || (enemyXCoordinate >= 633)) {
                    //if(repeat[2])
                    moveUp();
                }
                if (enemyXCoordinate < 10) {
                    //if(repeat[2])
                    moveDown();
                }
                break;

            case zoon4:
                if ((enemyXCoordinate >= 429 && enemyXCoordinate <= 453) || enemyXCoordinate <= 8) {
                    //if(repeat[3])
                    moveUp();
                }
                if ((enemyXCoordinate >= 188 && enemyXCoordinate <= 205) || (enemyXCoordinate >= 633)) {
                    //if(repeat[3])
                    moveDown();
                }
                break;

            case zoon5:
                if (enemyXCoordinate >= 244 && enemyXCoordinate <= 269) {
                    //if(repeat[4])
                    moveUp();
                }
                if ((enemyXCoordinate >= 429 && enemyXCoordinate <= 453) || enemyXCoordinate <= 8) {
                    //if(repeat[4])
                    moveDown();
                }
                break;

            case zoon6:
                if ((enemyXCoordinate >= 633) || enemyXCoordinate <= 7) {
                    //if(repeat[5])
                    moveUp();
                }
                if (enemyXCoordinate >= 244 && enemyXCoordinate <= 269) {
                    //if(repeat[5])
                    moveDown();
                }
                break;

            case zoon7:
                if (enemyXCoordinate >= 373 && enemyXCoordinate <= 397) {
                    //if(repeat[6])
                    moveUp();
                }
                if ((enemyXCoordinate >= 633) || enemyXCoordinate <= 7) {
                    //if(repeat[6])
                    moveDown();
                }
                break;

            case zoon8:
                if ((enemyXCoordinate >= 565 && enemyXCoordinate <= 589) || (enemyXCoordinate >= 245 && enemyXCoordinate <= 269)) {
                    //if(repeat[7])
                    moveUp();
                }
                if (enemyXCoordinate >= 373 && enemyXCoordinate <= 397) {
                    //if(repeat[7])
                    moveDown();
                }
                break;
            case zoon9:
                if ((enemyXCoordinate >= 565 && enemyXCoordinate <= 589) || (enemyXCoordinate >= 245 && enemyXCoordinate <= 269)) {
                    //if(repeat[8])
                    moveDown();
                }
                break;

            default:
                break;
        }//switch
        //System.out.println(moveDown);
        checkEnemyPacmanZoon();
		/*if(enemyXCoordinate>650)
		{
			//setRepeat();
			try
		    {
		        enemypacman1=new ImageIcon(getClass().getResource("/images/Enemy Pac/greenleftpac.gif"));
		    }
		    catch (Exception ex) {
		      ex.printStackTrace();
		    }
			enemyXCoordinate=650;
			movingRight=false;
			movingLeft=true;
		}
		else if(enemyXCoordinate<-5)
		{
			//setRepeat();
			
			try
		    {
		        enemypacman1=new ImageIcon(getClass().getResource("/images/Enemy Pac/greenrightpac.gif"));
		    }
		    catch (Exception ex) {
		      ex.printStackTrace();
		    }
			enemyXCoordinate=-5;
			movingRight=true;
			movingLeft=false;
		}*///this
		
		/*if(enemyXCoordinate>650)
		{
			enemyXCoordinate=-4;
			try
			{
				enemypacman1=new ImageIcon(getClass().getResource("/images/Enemy Pac/purpleleftpac.gif"));
			}catch(Exception e) {e.printStackTrace();}
			//movingLeft=false;
			//movingRight=true;
		}
		
		if(enemyXCoordinate<-5)
		{
			enemyXCoordinate=653;
			try
			{
				enemypacman1=new ImageIcon(getClass().getResource("/images/Enemy Pac/purplerightpac.gif"));
			}catch(Exception e) {e.printStackTrace();}
			//movingRight=false;
			//movingLeft=true;
		}*/

        if (enemyXCoordinate > 650) {
            if (random.nextInt(3) == 0) {
                try {
                    enemypacman1 = new ImageIcon(getClass().getResource("/images/Enemy Pac/greenleftpac.gif"));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                enemyXCoordinate = 650;
                movingRight = false;
                movingLeft = true;
            } else {
                enemyXCoordinate = -4;
                try {
                    enemypacman1 = new ImageIcon(getClass().getResource("/images/Enemy Pac/greenleftpac.gif"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //movingLeft=false;
                //movingRight=true;
            }
        }//if>650
        //&& (enemypacgameZoon!=PlayPanel.GAMEZOON.zoon3||enemypacgameZoon!=PlayPanel.GAMEZOON.zoon4)
        if (enemyXCoordinate < -5) {
            if (random.nextInt(3) == 0) {
                try {
                    enemypacman1 = new ImageIcon(getClass().getResource("/images/Enemy Pac/greenrightpac.gif"));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                enemyXCoordinate = -5;
                movingRight = true;
                movingLeft = false;
            } else {
                enemyXCoordinate = 650;
                try {
                    enemypacman1 = new ImageIcon(getClass().getResource("/images/Enemy Pac/greenrightpac.gif"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //movingRight=false;
                //movingLeft=true;
            }
        }//if<-5

        //changePac();

        if (moveUp) {
            changePac();
            //PlayPanel.GAMEZOON zoon=enemypacgameZoon;
            //System.out.println((zoon.toString()).substring(4));
            //movingRight=false;
            //movingLeft=false;
            switch (enemypacgameZoon) {
                case zoon1:
                    enemyYCoordinate -= 3;
                    if (enemyYCoordinate <= 466) {
                        moveUp = false;
                        if (random.nextInt(2) == 0) {
                            //System.out.println("Done");
                            movingRight = true;
                        } else {
                            //System.out.println("Done");
                            movingLeft = true;
                        }
                        break;
                    }
                    break;

                case zoon2:
                    enemyYCoordinate -= 3;
                    if (enemyYCoordinate <= 398) {
                        moveUp = false;
                        if (random.nextInt(2) == 0) {
                            //System.out.println("Done");
                            movingRight = true;
                        } else {
                            //System.out.println("Done");
                            movingLeft = true;
                        }
                        break;
                    }
                    break;

                case zoon3:
                    enemyYCoordinate -= 3;
                    if (enemyYCoordinate <= 330) {
                        moveUp = false;
                        if (random.nextInt(2) == 0) {
                            //System.out.println("Done");
                            movingRight = true;
                        } else {
                            //System.out.println("Done");
                            movingLeft = true;
                        }
                        break;
                    }
                    break;

                case zoon4:
                    enemyYCoordinate -= 3;
                    if (enemyYCoordinate <= 266) {
                        moveUp = false;
                        if (random.nextInt(2) == 0) {
                            //System.out.println("Done");
                            movingRight = true;
                        } else {
                            //System.out.println("Done");
                            movingLeft = true;
                        }
                        break;
                    }
                    break;

                case zoon5:
                    enemyYCoordinate -= 3;
                    if (enemyYCoordinate <= 198) {
                        moveUp = false;
                        if (random.nextInt(2) == 0) {
                            //System.out.println("Done");
                            movingRight = true;
                        } else {
                            //System.out.println("Done");
                            movingLeft = true;
                        }
                        break;
                    }
                    break;

                case zoon6:
                    enemyYCoordinate -= 3;
                    if (enemyYCoordinate <= 138) {
                        moveUp = false;
                        if (random.nextInt(2) == 0) {
                            //System.out.println("Done");
                            movingRight = true;
                        } else {
                            //System.out.println("Done");
                            movingLeft = true;
                        }
                        break;
                    }
                    break;

                case zoon7:
                    enemyYCoordinate -= 3;
                    if (enemyYCoordinate <= 74) {
                        moveUp = false;
                        if (random.nextInt(2) == 0) {
                            //System.out.println("Done");
                            movingRight = true;
                        } else {
                            //System.out.println("Done");
                            movingLeft = true;
                        }
                        break;
                    }
                    break;

                case zoon8:
                    enemyYCoordinate -= 3;
                    if (enemyYCoordinate <= 6) {
                        moveUp = false;
                        if (random.nextInt(2) == 0) {
                            //System.out.println("Done");
                            movingRight = true;
                        } else {
                            //System.out.println("Done");
                            movingLeft = true;
                        }
                        break;
                    }
                    break;
                default:
                    break;
            }
        }//moveUP
        if (moveDown) {
			/*changePac();			
			moveDown=false;
				if(random.nextInt(2)==0)
				{
					//System.out.println("Done");
					movingRight=true;
					movingLeft=false;
					moveUp=false;
					moveDown=false;
					//changePac();
				}
				else
				{
					//System.out.println("Done");
					movingLeft=true;
					movingRight=false;
					moveUp=false;
					moveDown=false;
					//changePac();
				}*/

            changePac();
            switch (enemypacgameZoon) {
                case zoon2:
                    enemyYCoordinate += 3;
                    if (enemyYCoordinate >= 522) {
                        moveDown = false;
                        if (random.nextInt(2) == 0) {
                            //System.out.println("Done");
                            movingRight = true;
                        } else {
                            //System.out.println("Done");
                            movingLeft = true;
                        }
                        break;
                    }
                    break;

                case zoon3:
                    enemyYCoordinate += 3;
                    if (enemyYCoordinate >= 454) {
                        moveDown = false;
                        if (random.nextInt(2) == 0) {
                            //System.out.println("Done");
                            movingRight = true;
                        } else {
                            //System.out.println("Done");
                            movingLeft = true;
                        }
                        break;
                    }
                    break;

                case zoon4:
                    enemyYCoordinate += 3;
                    if (enemyYCoordinate >= 386) {
                        moveDown = false;
                        if (random.nextInt(2) == 0) {
                            //System.out.println("Done");
                            movingRight = true;
                        } else {
                            //System.out.println("Done");
                            movingLeft = true;
                        }
                        break;
                    }
                    break;

                case zoon5:
                    enemyYCoordinate += 3;
                    if (enemyYCoordinate >= 322) {
                        moveDown = false;
                        if (random.nextInt(2) == 0) {
                            //System.out.println("Done");
                            movingRight = true;
                        } else {
                            //System.out.println("Done");
                            movingLeft = true;
                        }
                        break;
                    }
                    break;

                case zoon6:
                    enemyYCoordinate += 3;
                    if (enemyYCoordinate >= 254) {
                        moveDown = false;
                        if (random.nextInt(2) == 0) {
                            //System.out.println("Done");
                            movingRight = true;
                        } else {
                            //System.out.println("Done");
                            movingLeft = true;
                        }
                        break;
                    }
                    break;

                case zoon7:
                    enemyYCoordinate += 3;
                    if (enemyYCoordinate >= 194) {
                        moveDown = false;
                        if (random.nextInt(2) == 0) {
                            //System.out.println("Done");
                            movingRight = true;
                        } else {
                            //System.out.println("Done");
                            movingLeft = true;
                        }
                        break;
                    }
                    break;

                case zoon8:
                    enemyYCoordinate += 3;
                    if (enemyYCoordinate >= 130) {
                        moveDown = false;
                        if (random.nextInt(2) == 0) {
                            //System.out.println("Done");
                            movingRight = true;
                        } else {
                            //System.out.println("Done");
                            movingLeft = true;
                        }
                        break;
                    }
                    break;

                case zoon9:
                    enemyYCoordinate += 3;
                    if (enemyYCoordinate >= 62) {
                        moveDown = false;
                        if (random.nextInt(2) == 0) {
                            //System.out.println("Done");
                            movingRight = true;
                        } else {
                            //System.out.println("Done");
                            movingLeft = true;
                        }
                        break;
                    }
                    break;

                default:
                    break;
            }
        }//moveDown
        if (movingRight) {
            changePac();
            for (int i = 0; i <= 7; i++) {
                enemyXCoordinate++;
            }
        }
        if (movingLeft) {
            changePac();
            for (int i = 0; i <= 7; i++) {
                enemyXCoordinate--;
            }
        }
		
		
		/*switch(enemypacgameZoon)
		{
		case zoon2:
			if(enemyXCoordinate<=270 && enemyXCoordinate>=249)
			{
				moveDown();
			}
			break;
			
		case zoon3:
			if(enemyXCoordinate<10)
			{
				moveDown();
			}
			break;
			
		case zoon4:
			if((enemyXCoordinate>=188 &&enemyXCoordinate<=205) || (enemyXCoordinate>=633))
			{
				moveDown();
			}
			break;
			
		case zoon5://(xCoordinate>=318 &&xCoordinate<=330)
			if( (enemyXCoordinate>=429 && enemyXCoordinate<=453)|| enemyXCoordinate<=8)
			{
				moveDown();
			}
			break;
			
		case zoon6:
			if(enemyXCoordinate>=244&& enemyXCoordinate<=269)
			{
				moveDown();
			}
			break;
			
		case zoon7:
			if((enemyXCoordinate>=633) || enemyXCoordinate<=7)
			{
				moveDown();
			}
			break;
			
		case zoon8:
			if(enemyXCoordinate>=373&& enemyXCoordinate<=397)
			{
				moveDown();
			}
			break;
			
		case zoon9:
			if((enemyXCoordinate>=565 && enemyXCoordinate<=589) ||(enemyXCoordinate>=245 &&enemyXCoordinate<=269))
			{
				moveDown();
			}
			break;
			
			default:
				//yCoordinate--;
				//yCoordinate--;
				break;
		}//switchdown*/

    }//updateEnemyPacman()

    public void moveUp() {
        if (random.nextInt(2) == 0) {
            movingRight = false;
            movingLeft = false;
            moveDown = false;
            moveUp = true;
        } else
            moveUp = false;
        //System.out.println("Done");
    }

    public void moveDown() {
        if (random.nextInt(2) == 0) {
            movingRight = false;
            movingLeft = false;
            moveUp = false;
            moveDown = true;
        } else
            moveDown = false;
        //System.out.println("Done");
    }

    public void changePac() {
        if (moveUp) {
            try {
                enemypacman1 = new ImageIcon(getClass().getResource("/images/Enemy Pac/greenuppac.gif"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (movingLeft) {
            try {
                enemypacman1 = new ImageIcon(getClass().getResource("/images/Enemy Pac/greenleftpac.gif"));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (movingRight) {
            try {
                enemypacman1 = new ImageIcon(getClass().getResource("/images/Enemy Pac/greenrightpac.gif"));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (moveDown) {
            try {
                enemypacman1 = new ImageIcon(getClass().getResource("/images/Enemy Pac/greendownpac.gif"));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }//changePac()

    public void checkEnemyPacmanZoon() {
        if (EnemyPacmanGreen.enemyYCoordinate >= 522 && EnemyPacmanGreen.enemyYCoordinate <= 530) {
            EnemyPacmanGreen.enemypacgameZoon = PlayPanel.GAMEZOON.zoon1;
            PlayPanel.stateUndefined = false;
        } else if (EnemyPacmanGreen.enemyYCoordinate >= 454 && EnemyPacmanGreen.enemyYCoordinate <= 466) {
            EnemyPacmanGreen.enemypacgameZoon = PlayPanel.GAMEZOON.zoon2;
            PlayPanel.stateUndefined = false;
        } else if (EnemyPacmanGreen.enemyYCoordinate >= 386 && EnemyPacmanGreen.enemyYCoordinate <= 398) {
            EnemyPacmanGreen.enemypacgameZoon = PlayPanel.GAMEZOON.zoon3;
            PlayPanel.stateUndefined = false;
        } else if (EnemyPacmanGreen.enemyYCoordinate >= 322 && EnemyPacmanGreen.enemyYCoordinate <= 330) {
            EnemyPacmanGreen.enemypacgameZoon = PlayPanel.GAMEZOON.zoon4;
            PlayPanel.stateUndefined = false;
        } else if (EnemyPacmanGreen.enemyYCoordinate >= 254 && EnemyPacmanGreen.enemyYCoordinate <= 266) {
            EnemyPacmanGreen.enemypacgameZoon = PlayPanel.GAMEZOON.zoon5;
            PlayPanel.stateUndefined = false;
        } else if (EnemyPacmanGreen.enemyYCoordinate >= 194 && EnemyPacmanGreen.enemyYCoordinate <= 198) {
            EnemyPacmanGreen.enemypacgameZoon = PlayPanel.GAMEZOON.zoon6;
            PlayPanel.stateUndefined = false;
        } else if (EnemyPacmanGreen.enemyYCoordinate >= 130 && EnemyPacmanGreen.enemyYCoordinate <= 138) {
            EnemyPacmanGreen.enemypacgameZoon = PlayPanel.GAMEZOON.zoon7;
            PlayPanel.stateUndefined = false;
        } else if (EnemyPacmanGreen.enemyYCoordinate >= 62 && EnemyPacmanGreen.enemyYCoordinate <= 74) {
            EnemyPacmanGreen.enemypacgameZoon = PlayPanel.GAMEZOON.zoon8;
            PlayPanel.stateUndefined = false;
        } else if (EnemyPacmanGreen.enemyYCoordinate >= 0 && EnemyPacmanGreen.enemyYCoordinate <= 6) {
            EnemyPacmanGreen.enemypacgameZoon = PlayPanel.GAMEZOON.zoon9;
            PlayPanel.stateUndefined = false;
        } else {
            //System.out.println("Undefined");
            //EnemyPacman.moveUp=true;
            PlayPanel.stateUndefined = true;
            if (moveDown) {
                EnemyPacmanGreen.enemyYCoordinate++;
                EnemyPacmanGreen.enemyYCoordinate++;
            } else if (moveUp) {
                enemyYCoordinate--;
                enemyYCoordinate--;
            }
            //enemypacman.updateEnemyPacman();
		
		/*if(EnemyPacman.moveUp)
		{
			for(int i=0;i<=3;i++)
			EnemyPacman.enemyYCoordinate--;
		}
		else
		{
			EnemyPacman.enemyYCoordinate++;
			EnemyPacman.enemyYCoordinate++;
		}*/
        }
	/*if(stateUndefined)
	{
		if(EnemyPacman.moveUp)
		{
				EnemyPacman.enemyYCoordinate++;
		}
		
		else if(EnemyPacman.moveDown)
		{
			EnemyPacman.enemyYCoordinate--;
		}
	}*/
    }
	
	
	/*public void setRepeat()
	{
		switch(enemypacgameZoon)
		{
		case zoon1:
			for(int i=0;i<=8;i++)
				repeat[i]=false;
			repeat[0]=true;
			break;
			
		case zoon2:
			for(int i=0;i<=8;i++)
				repeat[i]=false;
			repeat[1]=true;
			break;
			
		case zoon3:
			for(int i=0;i<=8;i++)
				repeat[i]=false;
			repeat[2]=true;
			break;
			
		case zoon4:
			for(int i=0;i<=8;i++)
				repeat[i]=false;
			repeat[3]=true;
			break;
			
		case zoon5:
			for(int i=0;i<=8;i++)
				repeat[i]=false;
			repeat[4]=true;
			break;
			
		case zoon6:
			for(int i=0;i<=8;i++)
				repeat[i]=false;
			repeat[5]=true;
			break;
			
		case zoon7:
			for(int i=0;i<=8;i++)
				repeat[i]=false;
			repeat[6]=true;
			break;
			
		case zoon8:
			for(int i=0;i<=8;i++)
				repeat[i]=false;
			repeat[7]=true;
			break;
			
		case zoon9:
			for(int i=0;i<=8;i++)
				repeat[i]=false;
			repeat[8]=true;
			break;
			
		default:
			break;
		}
	}//setRepeat()*/

    public void Draw(Graphics2D g2d) {
        switch (PlayPanel.gameState) {
            case PLAYING:
                enemypacman1.paintIcon(this, g2d, enemyXCoordinate, enemyYCoordinate);
                break;
            default:
                break;
        }//switch
    }//draw()
}//class
