function b_addButton(img, label, action) {
  this.img[this.img.length]=img;
  this.lbl[this.lbl.length]=label;
  this.act[this.act.length]=action;
  this.sta[this.sta.length]=0;
  return this
}

//reset all panel buttons  (ns4, op5)
//alle Panel Buttons zur�cksetzten (ns4, op5)
function b_clear() {
var i
  for (i=0;i<this.sta.length;i++) {
    if (this.sta[i] != 0)
      this.mOut(i);
  }
}

//----------------------------------------------------------------------------
// Panel functions for Netscape 4
//----------------------------------------------------------------------------

// write new htmlcode into the button layer
// schreibe den neuen HTML Code in den Button Layer
function b_mOver_ns4(nr) {
  this.clear();
  l=this.obj.layers[0].layers[nr].document;
  l.open();
  if (this.smallicons) {
    l.write("<TABLE border=0><TR><TD width=25>")
    l.write("<SPAN class='imgbout'>")
    l.write("<A href='#' onmouseOut='"+this.v+".mOut("+nr+")' ");
    l.write("onMousedown='"+this.v+".mDown("+nr+")'><img width=22 height=22 src='"+this.img[nr]);
    l.write("' border=0></A></SPAN></TD><TD nowrap><A class=noline href=# onmouseOut='"+this.v+".mOut("+nr+")' ");
    l.write("onMousedown='"+this.v+".mDown("+nr+")'><Font class=imgB color="+this.color+">");
    l.write(this.lbl[nr]+"</FONT></A></TD></TR></TABLE><BR><BR>");
  }
  else {
    l.write("<Center>")
    l.write("<SPAN class='imgbout'>")
    l.write("<A href='#' onmouseOut='"+this.v+".mOut("+nr+")' ");
    l.write("onMousedown='"+this.v+".mDown("+nr+")'><img src='"+this.img[nr]);
    l.write("' border=0></A></SPAN><Font class=imgB color="+this.color+">");
    l.write(this.lbl[nr]+"</FONT><BR><BR>");
  }
  l.close();
  this.sta[nr]=1;
}

function b_mOut_ns4(nr) {
  l=this.obj.layers[0].layers[nr].document;
  l.open();
  if (this.smallicons) {
    l.write("<TABLE border=0><TR><TD width=25>")
    l.write("<SPAN class='imgnob'>")
    l.write("<A href='#' onmouseOver='"+this.v+".mOver("+nr+")' ");
    l.write("onmouseOut='"+this.v+".mOut("+nr+")'><img width=22 height=22 src='"+this.img[nr]);
    l.write("' border=0></A></SPAN></TD><TD nowrap>");
    l.write("<A class=noline href=# onmouseOver='"+this.v+".mOver("+nr+")' ");
    l.write("onmouseOut='"+this.v+".mOut("+nr+")'><Font class=imgB color="+this.color+">");
    l.write(this.lbl[nr]+"</FONT></A></TD></TR></TABLE><BR><BR>");
  }
  else {
    l.write("<Center>")
    l.write("<SPAN class='imgnob'>")
    l.write("<A href='#' onmouseOver='"+this.v+".mOver("+nr+")' ");
    l.write("onmouseOut='"+this.v+".mOut("+nr+")'><img src='"+this.img[nr]);
    l.write("' border=0></A></SPAN><Font class=imgB color="+this.color+">");
    l.write(this.lbl[nr]+"</FONT><BR><BR>");
  }
  l.close();
  this.sta[nr]=0;
}

function b_mDown_ns4(nr) {
  l=this.obj.layers[0].layers[nr].document;
  l.open();
  if (this.smallicons) {
    l.write("<TABLE border=0><TR><TD width=25>")
    l.write("<SPAN class='imgbin'>")
    l.write("<A href='#' onmouseOver='"+this.v+".mOver("+nr+")' ");
    l.write("onmouseOut='"+this.v+".mOut("+nr+")' onMouseup='"+this.act[nr]);
    l.write(";"+this.v+".mOver("+nr+")'><img width=22 height=22 src='"+this.img[nr]);
    l.write("' border=0></A></SPAN></TD><TD nowrap><A class=noline href=# onmouseOver='"+this.v+".mOver("+nr+")' ");
    l.write("onmouseOut='"+this.v+".mOut("+nr+")' onMouseup='"+this.act[nr]);
    l.write(";"+this.v+".mOver("+nr+")'><Font class=imgB color="+this.color+">");
    l.write(this.lbl[nr]+"</FONT></A></TD></TR></TABLE><BR><BR>");
  }
  else {
    l.write("<Center>")
    l.write("<SPAN class='imgbin'>")
    l.write("<A href='#' onmouseOver='"+this.v+".mOver("+nr+")' ");
    l.write("onmouseOut='"+this.v+".mOut("+nr+")' onMouseup='"+this.act[nr]);
    l.write(";"+this.v+".mOver("+nr+")'><img src='"+this.img[nr]);
    l.write("' border=0></A></SPAN><Font class=imgB color="+this.color+">");
    l.write(this.lbl[nr]+"</FONT><BR><BR>");
  }
  l.close();
  this.sta[nr]=1;
}

//test if scroll buttons should be visible
//teste ob Scroll-Buttons sichtbar sein sollen
function b_testScroll_ns4() {
var i
var j
var k

  i=this.obj.clip.bottom;
  j=this.obj.layers[0].clip.bottom;
  k=parseInt(this.obj.layers[0].top);

  if (k==this.captionheight+10)
    this.obj.layers[2].visibility='hide';
  else
    this.obj.layers[2].visibility='show';

  if ((j+k)<i) {
    this.obj.layers[3].visibility='hide';
  }
  else
    this.obj.layers[3].visibility='show';
}

//scroll the panel content up
//scrolle den Panel Inhalt nach Oben
function b_up_ns4(nr) {
    this.ftop = this.ftop - 5;
    this.obj.layers[0].top=this.ftop;
    nr--
    if (nr>0)
      setTimeout(this.v+'.up('+nr+');',10);
    else
      this.testScroll();
}

//scroll the panel content down
//scrolle den Panel Inhalt nach Unten
function b_down_ns4(nr) {
    this.ftop = this.ftop + 5;
    if (this.ftop>=this.captionheight+10) {
      this.ftop=this.captionheight+10;
      nr=0;
    }
    this.obj.layers[0].top=this.ftop;
    nr--

    if (nr>0)
      setTimeout(this.v+'.down('+nr+');',10);
    else
      this.testScroll();
}

//----------------------------------------------------------------------------
// Panel functions for Opera5
//----------------------------------------------------------------------------

//show one panelbutton layer and hide the others two
//zeige einen Panel Button Layer und verstecke die anderen beiden
function b_mOver_op5(nr) {
  var obj0=getObj(this.name+'_b'+nr+'0')
  var obj1=getObj(this.name+'_b'+nr+'1')
  var obj2=getObj(this.name+'_b'+nr+'2')

  this.clear();
  obj1.style.visibility="VISIBLE";
  obj0.style.visibility="HIDDEN";
  obj2.style.visibility="HIDDEN";
  this.sta[nr]=1;
}

function b_mOut_op5(nr) {
  var obj0=getObj(this.name+'_b'+nr+'0')
  var obj1=getObj(this.name+'_b'+nr+'1')
  var obj2=getObj(this.name+'_b'+nr+'2')

  obj2.style.visibility="visible";
  obj0.style.visibility="hidden";
  obj1.style.visibility="hidden";
  this.sta[nr]=1;
}

function b_mDown_op5(nr) {
  var obj0=getObj(this.name+'_b'+nr+'0')
  var obj1=getObj(this.name+'_b'+nr+'1')
  var obj2=getObj(this.name+'_b'+nr+'2')

  obj0.style.visibility="visible";
  obj1.style.visibility="hidden";
  obj2.style.visibility="hidden";
  this.sta[nr]=1;
}

// ---------------------------------------------------------------------------
// Panel functions for ie4, ie5, ns5, op5
// ---------------------------------------------------------------------------

//test if scroll buttons should be visible
//teste ob Scroll-Buttons sichtbar sein sollen
function b_testScroll() {

  if (bt.op5) {
    var i=parseInt(this.obj.style.pixelHeight);
    var j=parseInt(this.objf.style.pixelHeight);
  }
  else {
    var i=parseInt(this.obj.style.height);
    var j=parseInt(this.objf.style.height);
  }
  var k=parseInt(this.objf.style.top);


  if (k==this.captionheight+10)
    this.objm1.style.visibility='hidden';
  else
    this.objm1.style.visibility='visible';

  if ((j+k)<i) {
    this.objm2.style.visibility='hidden';
  }
  else
    this.objm2.style.visibility='visible';
}

//scroll the panel content up
//scrolle den Panel Inhalt nach Oben
function b_up(nr) {
    this.ftop = this.ftop - 5;
    this.objf.style.top=this.ftop;
    nr--
    if (nr>0)
      setTimeout(this.v+'.up('+nr+');',10);
    else
      this.testScroll();
}

//scroll the panel content down
//scrolle den Panel Inhalt nach Unten
function b_down(nr) {
    this.ftop = this.ftop + 5;
    if (this.ftop>=this.captionheight+10) {
      this.ftop=this.captionheight+10;
      nr=0;
    }
    this.objf.style.top=this.ftop;

    nr--

    if (nr>0)
      setTimeout(this.v+'.down('+nr+');',10);
    else
      this.testScroll();
}

// ---------------------------------------------------------------------------
// Panel object
// ---------------------------------------------------------------------------

//create one panel
function createPanel(name,caption,link) {
  this.name=name;                  // panel layer ID
  this.ftop=0;                     // actual panel scroll position
  this.captionheight=0;            // height of the panels caption button
  this.color=0;                    // text color of the panels
  this.obj=null;                   // panel layer object
  this.objc=null;                  // caption layer object
  this.objf=null;                  // panel field layer object
  this.objm1=null;                 // scroll button up
  this.objm2=null;                 // scroll button down
  this.caption=caption;            // panel caption
  this.link=link;                  // link for panel caption
  this.smallicons=false;           // use small icons?
  this.img=new Array();            // button images
  this.lbl=new Array();            // button labels
  this.act=new Array();            // button actions
  this.sta=new Array();            // button status (internal)
  this.addButton=b_addButton;      // add one button to panel
  this.clear=b_clear;              // reset all buttons
  if (bt.ns4) {                          // functions for ns4
    this.mOver=b_mOver_ns4;              // handles mouseOver event
    this.mOut=b_mOut_ns4;                // handles mouseOut & mouseUp event
    this.mDown=b_mDown_ns4;              // handles mouseDown event
    this.testScroll=b_testScroll_ns4;    // test if scroll buttons visible
    this.up=b_up_ns4;                    // scroll panel buttons up
    this.down=b_down_ns4;                // scroll panel buttons down
  }
  if (bt.op5) {                          // functions for op5
    this.mOver=b_mOver_op5;              // handles mouseOver event
    this.mOut=b_mOut_op5;                // handles mouseOut & mouseUp event
    this.mDown=b_mDown_op5;              // handles mouseDown event
  }
  if (!bt.ns4) {                     // functions for all browsers but ns4
    this.testScroll=b_testScroll;    // test if scroll buttons should be visible
    this.up=b_up;                    // scroll panel buttons up
    this.down=b_down;                // scroll panel buttons down
  }

  this.v = this.name + "var";   // global var of 'this'
  eval(this.v + "=this");

  return this
}

//add one panel to the outlookbar
function b_addPanel(panel) {
  panel.name=this.name+'_panel'+this.panels.length
  panel.captionheight=this.captionheight;
  panel.ftop=this.captionheight+10;
  panel.color=this.color;
  panel.smallicons=this.smallicons;
  this.panels[this.panels.length] = panel;
}

//write style sheets
//schreibe die Style sheets
function b_writeStyle() {

  document.write('<STYLE TYPE="text/css">');

  document.write('.button {text-align:center; vertical-align:middle; font-family:arial;');
  document.write(' font-size:12pt; cursor:hand; border-width:3;');
  document.write(' border-style:outset; border-color:'+this.captionbgcolor+'; ');
  document.write('background-color:'+this.captionbgcolor+';}');


  document.write('.noLine {text-decoration:none;}');

  document.write('.captiontxt {font-family:\''+this.captionfont+'\'; font-size:'+ this.captionfontsize+'; text-decoration:none;}');

  document.write('.imgB {color:'+this.color+'; text-decoration:none; font-family:\''+this.buttonfont+'\'; font-size:'+this.buttonfontsize+';}');

  document.write('.imgbin {border-width:2; border-style:inset; ');
  document.write('border-color:'+this.bordercolor+';}');

  document.write('.imgbout {border-width:2; border-style:outset; ');
  document.write('border-color:'+this.bordercolor+';}');

  document.write('.imgnob {border-width:2; border-style:solid; ');
  document.write('border-color:'+this.bgcolor+';}');

  document.write('</STYLE>');

}

// Draw the Outlook Bar
function b_draw() {
var i;
var j;
var t=0;
var h;
var c=0;

  this.writeStyle();



  c=0;
  if (bt.ns5 || bt.op5) c=6;       //two times border width

  if (bt.ns4) {                 //draw OutlookBar for ns4
    //OutlookBar layer..
    document.write('<layer bgcolor='+this.bgcolor+' name='+this.name+' left=');
    document.write(this.xpos+' top='+this.ypos+' width='+this.width);
    document.write(' clip="0,0,'+this.width+','+this.height+'">');

    //one layer for every panel...
    for (i=0;i<this.panels.length;i++) {
      document.write('<Layer name='+this.name+'_panel'+i+' background=\''+this.bgimage+'\' width='+this.width);
       document.write(' top='+i*this.captionheight+' bgcolor='+this.bgcolor);
       document.write(' clip="0,0,'+this.width+',');
       document.write(this.height-(this.panels.length-1)*this.captionheight+'" >');

       //one layer to host the panel buttons
       document.write('<Layer top='+(this.captionheight+10)+' width='+this.width+'>');
        mtop=0

        //one layer for every button
        for (j=0;j<this.panels[i].img.length;j++) {
          if (this.smallicons) {
            document.write('<Layer top='+mtop+' width='+this.width);
            document.write("><TABLE border=0><TR><TD width=25>");
            document.write("<SPAN class='imgnob'>");
            document.write("<A href='#' onmouseOver='"+this.panels[i].v+".mOver("+j+")' ");
            document.write("onmouseOut='"+this.panels[i].v+".mOut("+j+")'><img width=22 height=22 src='"+this.panels[i].img[j]);
            document.write("' border=0></A></SPAN></TD><TD nowrap><A class=noline href=# onmouseOver='"+this.panels[i].v+".mOver("+j+")' ");
            document.write("onmouseOut='"+this.panels[i].v+".mOut("+j+")'><Font class=imgB>");
            document.write(this.panels[i].lbl[j]+"</FONT></A></TD></TR></TABLE><BR><BR>");
            document.write('</Layer>');
          }
          else {
            document.write('<Layer top='+mtop+' width='+this.width);
            document.write('><Center><SPAN class=imgnob>');
            document.write("<A href='#' onmouseOut='"+this.panels[i].v);
            document.write(".rst("+j+")' onmouseOver='"+this.panels[i].v);
            document.write(".mOver("+j+")'><img src='"+this.panels[i].img[j]);
            document.write("' border=0></A></SPAN>");
            document.write("<Font class=imgB>");
            document.write(this.panels[i].lbl[j]+"</FONT><BR><BR>");
           document.write('</Layer>');
          }
          mtop=mtop+this.buttonspace;
        }

       document.write('</Layer>');

       //one layer for the panels caption
       document.write('<Layer top=0 width='+this.width+' clip="0,0,');
       document.write(this.width+','+this.captionheight+'" bgcolor='+this.captionbgcolor);
       document.write(' class=button onmouseOver="'+this.panels[i].v+'.clear();">');
       document.write('<TABLE border=0 cellspacing=0 cellpadding=0 width=');
       document.write((this.width-12)+' height='+(this.captionheight-12)+'>')
       document.write('<TR><TD valign=middle align=Center>');
       document.write('<A class=noLine href=\'javascript:'+this.v+'.showPanel(');
       document.write(i+');\' onmouseDown=\''+this.panels[i].link+';\' onmouseOver="'+this.panels[i].v+'.clear();">');
       document.write('<Font Color='+this.captioncolor+' class=captiontxt>'+this.panels[i].caption);
       document.write('</Font></A></TD></TR></TABLE></Layer>');

       //two layers for scroll-up -down buttons
       document.write('<Layer visibility=hide top='+(this.captionheight+12)+' left='+(this.width-20));
       document.write('><A href="#" onClick="'+this.panels[i].v+'.down('+(this.buttonspace / 5)+');" ');
       document.write('onmouseOver="'+this.panels[i].v+'.clear();"><img ');
       document.write('width=16 height=16 src=arrowup.gif border=0>');
       document.write('</A></LAYER>');
       document.write('<Layer top=');
       document.write((this.height-(this.panels.length-1)*this.captionheight-30)+' left=');
       document.write((this.width-20)+'><A href="#" onClick="');
       document.write(this.panels[i].v+'.up('+(this.buttonspace/5)+');" onmouseOver="');
       document.write(this.panels[i].v+'.clear();"><img width=16 height=16 ');
       document.write('src=arrowdown.gif border=0></A></LAYER>');

      document.write('</LAYER>');
    }
    document.write('</LAYER>');
  }
  else {                             //draw Outlook bar for all browsers but ns4

    //OutlookBar layer..
    document.write('<DIV id='+this.name+' Style="position:absolute; left:');
    document.write(this.xpos+'; top:'+this.ypos+'; width:'+this.width);
    document.write('; height:'+this.height+'; background-color:'+this.bgcolor)
    document.write('; clip:rect(0,'+this.width+','+this.height+',0)">');
    h=this.height-((this.panels.length-1)*this.captionheight)

    //one layer for every panel...
    for (i=0;i<this.panels.length;i++) {
      document.write('<DIV id='+this.name+'_panel'+i);
      document.write(' Style="position:absolute; left:0; top:'+t);
      document.write('; width:'+this.width+'; height:'+h+'; clip:rect(0px, ');
      document.write(this.width+'px, '+h+'px, 0px); ');
      document.write('background-color:'+this.bgcolor+'; background-image:url('+this.bgimage+');">')
      t=t+this.captionheight;

       //one layer to host the panel buttons
      document.write('<div id='+this.name+'_panel'+i);
      document.write('_f Style="position:absolute; left:0; top:'+(this.captionheight+10)+'; width:');
      document.write(this.width+'; height:');
      document.write((this.panels[i].img.length*this.buttonspace));
      document.write(';">')
      mtop=0

      //one (ie4, ie5, ns5) or three layers (op5) for every button
      for (j=0;j<this.panels[i].img.length;j++) {
        if (bt.op5) {
          if (this.smallicons) {
            document.write('<DIV id='+this.name+'_panel'+i+'_b'+j);
            document.write('0 class=imgB Style="position:absolute; ');
            document.write('visibility:hidden; left:10; width:'+this.width);
            document.write('; top:'+mtop+'; text-align:left;">');
            document.write('<TABLE border=0><TR><TD><img src='+this.panels[i].img[j]);
            document.write(' width=22 height=22 class=imgbin onmouseUp=\''+this.panels[i].v);
            document.write('.mOver('+j+');'+this.panels[i].act[j]);
            document.write(';\' onmouseOut="'+this.panels[i].v+'.mOut('+j);
            document.write(');"></TD><TD valign=middle nowrap><A href=# class=imgB ');
            document.write('onmouseOut="'+this.panels[i].v+'.mOut('+j+')" onmouseDown="'+this.panels[i].v+'.mDown('+j+')" onmouseUp="'+this.panels[i].v+'.mOver('+j+'); " onmouseOver="'+this.panels[i].v+'.mOver('+j+')">'+this.panels[i].lbl[j]+'</A></TD></TR></TABLE></DIV>');

            document.write('<DIV id='+this.name+'_panel'+i+'_b'+j+'1 class=imgB');
            document.write(' Style="position:absolute; visibility:hidden; ');
            document.write('left:10; width:'+this.width+'; top:'+mtop);
            document.write('; text-align:left;">');
            document.write('<TABLE border=0><TR><TD><img src='+this.panels[i].img[j]);
            document.write(' width=22 height=22 class=imgbout onmouseDown="'+this.panels[i].v);
            document.write('.mDown('+j+');" onmouseUp=\''+this.panels[i].v);
            document.write('.mOver('+j+');'+this.panels[i].act[j]);
            document.write(';\' onmouseOut="'+this.panels[i].v+'.mOut('+j);
            document.write(');"></TD><TD valign=middle nowrap><A href=# class=imgB ');
            document.write('onmouseOut="'+this.panels[i].v+'.mOut('+j+')" onmouseDown="'+this.panels[i].v+'.mDown('+j+')" onClick="return false" onmouseUp=\''+this.panels[i].v+'.mOver('+j+'); '+this.panels[i].act[j]+'\' onmouseOver="'+this.panels[i].v+'.mOver('+j+')">'+this.panels[i].lbl[j]+'</A></TD></TR></TABLE></DIV>');

            document.write('<DIV id='+this.name+'_panel'+i+'_b'+j);
            document.write('2 class=imgB Style="position:absolute; ');
            document.write('visibility:visible; left:10; width:'+this.width);
            document.write('; top:'+mtop+'; text-align:left;">');
            document.write('<TABLE border=0><TR><TD><img src='+this.panels[i].img[j]+' width=22 height=22 class=imgnob ');
            document.write('onmouseOver="'+this.panels[i].v+'.mOver('+j);
            document.write(');"></TD><TD valign=middle nowrap><A href=# class=imgB ');
            document.write('onmouseOut="'+this.panels[i].v+'.mOut('+j+')" onmouseDown="'+this.panels[i].v+'.mDown('+j+')" onmouseUp="'+this.panels[i].v+'.mOver('+j+');" onmouseOver="'+this.panels[i].v+'.mOver('+j+')">'+this.panels[i].lbl[j]+'</A></TD></TR></TABLE></DIV>');
          }
          else {
            document.write('<DIV id='+this.name+'_panel'+i+'_b'+j);
            document.write('0 class=imgB Style="position:absolute; ');
            document.write('visibility:hidden; left:0; width:'+this.width);
            document.write('; top:'+mtop+'; text-align:center;">');
            document.write('<img src='+this.panels[i].img[j]);
            document.write(' class=imgbin onmouseUp=\''+this.panels[i].v);
            document.write('.mOver('+j+');'+this.panels[i].act[j]);
            document.write(';\' onmouseOut="'+this.panels[i].v+'.mOut('+j);
            document.write(');"><BR>'+this.panels[i].lbl[j]+'</DIV>');

            document.write('<DIV id='+this.name+'_panel'+i+'_b'+j+'1 class=imgB');
            document.write(' Style="position:absolute; visibility:hidden; ');
            document.write('left:0; width:'+this.width+'; top:'+mtop);
            document.write('; text-align:center;">');
            document.write('<img src='+this.panels[i].img[j]);
            document.write(' class=imgbout onmouseDown="'+this.panels[i].v);
            document.write('.mDown('+j+');" onmouseUp=\''+this.panels[i].v);
            document.write('.mOver('+j+');'+this.panels[i].act[j]);
            document.write(';\' onmouseOut="'+this.panels[i].v+'.mOut('+j);
            document.write(');"><BR>'+this.panels[i].lbl[j]+'</DIV>');

            document.write('<DIV id='+this.name+'_panel'+i+'_b'+j);
            document.write('2 class=imgB Style="position:absolute; ');
            document.write('visibility:visible; left:0; width:'+this.width);
            document.write('; top:'+mtop+'; text-align:center;">');
            document.write('<img src='+this.panels[i].img[j]+' class=imgnob ');
            document.write('onmouseOver="'+this.panels[i].v+'.mOver('+j);
            document.write(');"><BR>'+this.panels[i].lbl[j]+'</DIV>');
          }
        }
        else {
          document.write('<DIV id='+this.name+'_panel'+i+'_b'+j+' class=imgB ');
          document.write('Style="position:absolute; left:5; width:'+this.width);
          if (this.smallicons) {
            document.write('; top:'+mtop+'; text-align:left;">');
            document.write('<TABLE border=0><TR><TD width=23><img id=IMG'+i+j+' src='+this.panels[i].img[j]+' width=22 height=22 class=imgnob ');
            document.write('onmouseOver="this.className=\'imgbout\';" ');
            document.write('onmouseDown="this.className=\'imgbin\';" ');
            document.write('onmouseUp=\'this.className="imgbout";');
            document.write(this.panels[i].act[j]+';\' ');
            document.write('onmouseOut="this.className=\'imgnob\';">');
            document.write('</TD><TD valign=middle nowrap><A href=# class=imgB ');
            document.write('onmouseOver="getObj(\'IMG'+i+j+'\').className=\'imgbout\';" ');
            document.write('onmouseOut="getObj(\'IMG'+i+j+'\').className=\'imgnob\';" ');
            document.write('onmouseDown="getObj(\'IMG'+i+j+'\').className=\'imgbin\';" ');
            document.write('onmouseUp="getObj(\'IMG'+i+j+'\').className=\'imgbout\';" ');
            document.write('onClick=\''+this.panels[i].act[j]+'; this.blur();\' ');
            document.write('>'+this.panels[i].lbl[j]+'</A></TD></TR></TABLE></DIV>');
          }
          else {
            document.write('; top:'+mtop+'; text-align:center;">');
            document.write('<img src='+this.panels[i].img[j]+' class=imgnob ');
            document.write('onmouseOver="this.className=\'imgbout\';" ');
            document.write('onmouseDown="this.className=\'imgbin\';" ');
            document.write('onmouseUp=\'this.className="imgbout";');
            document.write(this.panels[i].act[j]+';\' ');
            document.write('onmouseOut="this.className=\'imgnob\';"><BR>');
            document.write(this.panels[i].lbl[j]+'</DIV>');
          }
        }
        mtop=mtop+this.buttonspace;
      }

      document.write('</DIV>');

      //one layer for the panels caption if not op5!
      if (!bt.op5) {
        document.write('<DIV id='+this.name+'_panel'+i+'_c class=button ');
        document.write('onClick=\''+this.v+'.showPanel('+i+');'+this.panels[i].link+';\' ')
        document.write('style="position:absolute; left:0; top:0; width:');
        document.write((this.width-c)+'; height:'+(this.captionheight-c)+';">');
        document.write('<TABLE width=100% border=0 cellspacing=0 cellpading=0 ');
        document.write(' height=100%');
        document.write('><TR><TD align=center valign=middle>');
        document.write('<A href="#" ');
        document.write('onClick=\''+this.v+'.showPanel('+i+');'+this.panels[i].link+';this.blur();');
        document.write('return false;\' class=noLine><FONT color=');
        document.write(this.captioncolor);
        document.write(' class=captiontxt>'+this.panels[i].caption);
        document.write('</FONT></A></TD></TR></TABLE></DIV>')
      }
      //two layers for scroll-up -down buttons
      document.write('<DIV id='+this.name+'_panel'+i);
      document.write('_m1 style="position:absolute; top:'+(this.captionheight+12)+'; left:');
      document.write((this.width-20)+';"><A href="#" onClick="');
      document.write(this.panels[i].v+'.down('+(this.buttonspace / 5)+');this.blur();return false;" ');
      document.write('onmouseOver="'+this.panels[i].v+'.clear();">');
      document.write('<img width=16 height=16 src=images/arrowup.gif border=0>');
      document.write('</A></DIV>');
      document.write('<DIV id='+this.name+'_panel'+i);
      document.write('_m2 style="position:absolute;  top:');
      document.write((this.height-(this.panels.length-1)*this.captionheight-30)+'; left:');
      document.write((this.width-20)+';"><A href="#" onClick="');
      document.write(this.panels[i].v+'.up('+(this.buttonspace / 5)+');this.blur();return false" ');
      document.write('onmouseOver="'+this.panels[i].v+'.clear();">');
      document.write('<img width=16 height=16 src=images/arrowdown.gif border=0>');
      document.write('</A></DIV>');


      document.write('</DIV>')

    }
    //Opera bug (Clip!)
    //op5 doesn't support layer clipping! so use top layers for panel caption
    //and two top layers with background-color like page color to hide
    //panel content outside of the outlookbar.
    //op5 unterst�tzt kein Clip bei Layers! darum erzeugen wir drei top level
    //layers f�r die Panel �berschrift und zwei top Layers mit der gleichen
    //Hintergrundfarbe wie die HTML Seite um den Panel Inhalt au�erhalb des
    //Outlook Bars zu verdecken!
    if (bt.op5) {
      //one layers for panel captions if op5
      for (i=0;i<this.panels.length;i++) {
        document.write('<DIV id='+this.name+'_panel'+i);
        document.write('_c class=button onmouseOver="'+this.panels[i].v);
        document.write('.clear();" onClick=\''+this.v+'.showPanel('+i+');'+this.panels[i].link+';\' ');
        document.write('style="position:absolute; left:0; top:0; width:');
        document.write((this.width-c)+'; height:'+(this.captionheight-c)+';">');
        document.write('<TABLE width=100% border=0 cellspacing=0 cellpading=0 ');
        document.write(' height=100%');
        document.write('><TR><TD align=center valign=middle>');
        document.write('<A href="#" ');
        document.write('onClick=\''+this.v+'.showPanel('+i+');'+this.panels[i].link+';this.blur();');
        document.write('return false;\' class=noLine><FONT color='+this.captioncolor);
        document.write(' class=captiontxt>'+this.panels[i].caption);
        document.write('</FONT></A></TR></TD></TABLE></DIV>')
      }
      //two layers to hide 'nonvisible' part of panel
      //(op5 doesn't support clipping!)
      //document.write('<DIV style="position:absolute; left:0; top:');
      //document.write(this.height+'; height:300; width:'+this.width);
      //document.write('; background-color:'+this.pagecolor+';"></DIV>');
      //document.write('<DIV style="position:absolute; left:0; top:-300; ');
      //document.write('height:300; width:'+this.width+'; background-color:');
      //document.write(this.pagecolor+';"></DIV>');
    }
    document.write('</DIV>');

  }
  for (i=0;i<this.panels.length;i++) {
    this.panels[i].obj=getObj(this.name+'_panel'+i);
    if (!bt.ns4) {
      this.panels[i].objc=getObj(this.name+'_panel'+i+'_c');
      this.panels[i].objf=getObj(this.name+'_panel'+i+'_f');
      this.panels[i].objm1=getObj(this.name+'_panel'+i+'_m1');
      this.panels[i].objm2=getObj(this.name+'_panel'+i+'_m2');
    }
    this.panels[i].testScroll();
  }

  //activate last panel
  //op5 dosen't support cookies!
  //so get actual panel from url paramter
  if (bt.op5) {
    if (document.location.search=='')  {
      this.showPanel(0);
    }
    else
      this.showPanel(document.location.search.substr(1,1));
  }
  else {
    //actual panel is saved in a cookie
    if (document.cookie)
      this.showPanel(document.cookie);
    else
      this.showPanel(0);
  }
}


// ---------------------------------------------------------------------------
// outlookbar function for ns4
// ---------------------------------------------------------------------------

function b_showPanel_ns4(nr) {
var i
var l
  document.cookie=nr;
  l = this.panels.length;
  for (i=0;i<l;i++) {
    if (i>nr) {
      this.panels[i].obj.top=this.height-((l-i)*this.captionheight)-1;
    }
    else {
      this.panels[i].obj.top=i*this.captionheight;
    }
  }
}

// ---------------------------------------------------------------------------
// outlookbar function for ie4, ie5, ns5, op5
// ---------------------------------------------------------------------------

function b_showPanel(nr) {
var i
var l
var o
  document.cookie=nr;
  this.aktPanel=nr;
  l = this.panels.length;
  for (i=0;i<l;i++) {
    if (i>nr) {
      this.panels[i].obj.style.top=this.height-((l-i)*this.captionheight);
      //Opera doesn't support clip:rect()!
      //so hide non visible panels
      //and move panel caption
      if (bt.op5) {
        this.panels[i].objf.style.visibility='hidden';
        this.panels[i].objc.style.top=this.height-((l-i)*this.captionheight);
      }
    }
    else {
      this.panels[i].obj.style.top=i*this.captionheight;
      //Opera doesn't support clip:rect()!
      //so show visible panel
      //and move panel caption
      if (bt.op5) {
        this.panels[i].objf.style.visibility='visible';
        this.panels[i].objc.style.top=i*this.captionheight;
      }
    }
  }
}

//resize the Outlook Like Bar
//IE4/5 & NS6 -> resize all layers (width & height)
//op5         -> resize only height - reload on width change
//ns4         -> reload on any change!
//
//if you change the width of a layer (style="text-align:center;") then
//the content will not be moved!
function b_resize(x,y,width,height) {
var o
var i
var j
var h
var c=(bt.ns5)?6:0;

   if (bt.ns4)
     location.reload();
   else {
     if (bt.op5 && (width!=this.width))
       if (location.href.indexOf('?')!=-1)
         location.href=location.href.replace(/\?./,"?"+this.aktPanel)
       else
         location.href= location.href+'?'+this.aktPanel;
     else {
       this.xpos=x;
       this.yPos=y;
       this.width=width
       if (!(bt.ns4 || bt.ns5 || bt.op5)) this.width = this.width - 2;
       this.height=height

       o=getObj(this.name);
       o.style.left=x;
       o.style.top=y;
       o.style.width=width;
       o.style.height=height;
       o.style.clip='rect(0px '+this.width+'px '+this.height+'px 0px)';

       h=this.height-((this.panels.length-1)*this.captionheight)

       for (i=0; i<this.panels.length; i++) {

         o=getObj(this.name+'_panel'+i+'_c');
         o.style.width=(this.width-c);

         if (!bt.op5)
           for (j=0;j<this.panels[i].img.length;j++) {
             o=getObj(this.name+'_panel'+i+'_b'+j);
             o.style.width=this.width;
           }

         this.panels[i].objm1.style.left=(this.width-20);
         this.panels[i].objm2.style.top=(this.height-(this.panels.length)*this.captionheight);
         this.panels[i].objm2.style.left=(this.width-20);
         this.panels[i].objf.style.width=this.width;
         this.panels[i].obj.style.width=this.width
         this.panels[i].obj.style.height=h
         this.panels[i].obj.style.pixelHeight=h
         this.panels[i].obj.style.clip='rect(0px '+this.width+'px '+h+'px 0px)';

         this.panels[i].testScroll();
       }
     }
     this.showPanel(this.aktPanel);
   }
}



// ---------------------------------------------------------------------------
// OutlookBar object for ie4, ie5, ns5, op5
// ---------------------------------------------------------------------------

function createOutlookBar(name,x,y,width,height,bgcolor,pagecolor,bgimage) {
  this.aktPanel=0;                        // last open panel
  this.name=name                          // OutlookBar name
  this.xpos=x;                            // bar x-pos
  this.ypos=y;                            // bar y-pos
  this.width=width;                       // bar width
  if (bt.ns4) this.width=this.width+2;
  if (!(bt.ns4 || bt.ns5 || bt.op5)) this.width = this.width - 2;
  this.height=height;                     // bar height
  this.bgcolor=bgcolor;                   // bar background color
  this.bgimage=bgimage;                   //Backgound Image
  this.color='white';
  this.bordercolor='silver';
  this.pagecolor=pagecolor;               // page bgcolor (op5!)
  this.captionbgcolor='silver';
  this.captioncolor='black';
  this.captionheight=28;
  this.buttonspace=80                     // distance of panel buttons
  this.smallicons=false;                  // use small icons?
  this.captionfont='Arial';
  this.captionfontsize='12pt';
  this.buttonfont='Arial';
  this.buttonfontsize='10pt';
  this.labe
  this.panels=new Array()                 // OutlookBar panels
  this.addPanel=b_addPanel;               // add new panel to bar
  this.writeStyle=b_writeStyle;
  this.draw=b_draw;                       // write HTML code of bar
  if (bt.ns4)
    this.showPanel=b_showPanel_ns4;       // make a panel visible (ns4)
  else
    this.showPanel=b_showPanel;           // make a panel visible (!=ns4)
    
  this.resize=b_resize;                   // resize Outlook Like Bar

  this.v = name + "var";                  // global var of 'this'
  eval(this.v + "=this");

  return this
}

