int checkInventory;
int checkItem;
float inix;
float iniy;
int invy;
int invx;
Grid grid;
Item potion, sword, bow, shield;
int[] [] inventory;

void setup()
{
  size(600,600);
  checkItem = 0;
  checkInventory = 0;
  grid = new Grid (275,100,4,3);
  potion = new Item (25,25,100,100,0,255,0,1);
  sword = new Item (25,150,100,200,255,0,0,2);
  bow = new Item (150,37,100,300,255,255,0,3);
  shield = new Item (37,375,200,200,0,0,255,4);
}

void draw()
{
  background(0);
  grid.drawGrid();
  shield.drawItem();
  bow.drawItem();
  sword.drawItem();
  potion.drawItem();
  potion.pickUp();
  sword.pickUp();
  bow.pickUp();
  shield.pickUp();
}

void mouseReleased()
{
  switch (checkInventory)
  {
    case 1:
      potion.drop();
    case 2:
      sword.drop(); 
    case 3:
      bow.drop();
    case 4:
      shield.drop();
  }  
}
