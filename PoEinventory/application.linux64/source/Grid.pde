class Grid
{
  Grid (float _inix, float _iniy, int _invy, int _invx)
  {
    inix=_inix;
    iniy=_iniy;
    invy=_invy;
    invx=_invx;
    inventory = new int [invy][invx];
  }
  void drawGrid ()
  {
    for (float i = inix; i <= (inix+invx*100); i= i+100)
    {
      stroke(255);
      strokeWeight(2);
      line(i,iniy,i,iniy+invy*100);
    }
    for (float i = iniy; i <= (iniy+invy*100); i= i+100)
    {
      stroke(255);
      strokeWeight(2);
      line(inix,i,inix+invx*100,i);
    }
  }
}
