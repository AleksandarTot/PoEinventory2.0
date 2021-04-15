void deslot(int i, int j, float z1, float z2)
{
    inventory[i][j]=0;
    if(z1>100 && z2>100)
    {
      inventory[i][j+1]=0;
      inventory[i+1][j]=0;
      inventory[i+1][j+1]=0;
    }
    else if(z1>200)
    {
      inventory[i][j+1]=0;
      inventory[i][j+2]=0;
    }
    else if(z2>200)
    {
      inventory[i+1][j]=0;
      inventory[i+2][j]=0;
    }
    else if(z1>100)
    {
      inventory[i][j+1]=0;
    }
    else if(z2>100)
    {
      inventory[i+1][j]=0;
    }
}
