boolean test (int i,int j,float z1,float z2)
{
  if (inventory[i][j]==1)
  {
    return false;
  }
  else if (z1>100 && z2>100 && inventory[i+1][j+1]==1)
  {
    return false;
  }
  else if (z1>200 && inventory[i][j+2]==1)
  {
    return false;
  }
  else if (z2>200 && inventory[i+2][j]==1)
  {
    return false;
  }
  else if (z1>100 && inventory[i][j+1]==1)
  {
    return false;
  }
  else if (z2>100 && inventory[i+1][j]==1)
  {
    return false;
  }
  else return true;
}
