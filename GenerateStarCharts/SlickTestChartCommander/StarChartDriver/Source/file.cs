using System;
using System.Windows.Forms;
using UIControls;
using System.Threading;

	public class TestRunner
	{
		static void Main(string[] args)
		{
			new SetPositionDriver(new string[]{"22",  "45", "12"}, new string[]{"23", "43", "01"});
			Thread.Sleep(1000);
			new MakePdfDriver("C:\\users\\joe.rowley\\Desktop\\test.pdf");
		}
	}

//****Add the following line of code into shared main(): 
//SetPositionDriver TestInstance_SetPositionDriver = new SetPositionDriver();

#region "Description (SetPositionDescriptors) was generated via the recorder..."
public class SetPositionDescriptors{
	//Description Code Below
	public static UIControls.Description MainWindow = UIControls.Description.Create(@"name:=""Window"";;value:=""Cartes du Ciel - Chart_1""");//Top level object
	public static UIControls.Description Main_Sub_Window = UIControls.Description.Create(@"name:=""Window"";;value:="""";;index:=""1""");//Parent's name: Window_Cartes_du_Ciel_HYPN_Chart_1
	public static UIControls.Description Window_Position = UIControls.Description.Create(@"name:=""Window"";;value:=""Position""");//Top level object
	public static UIControls.Description Equatorial_Coords_Frame = UIControls.Description.Create(@"name:=""Window"";;index:=""8""");//Parent's name: Window_Position
	public static UIControls.Description RA_Field_1 = UIControls.Description.Create(@"windowtype:=""textbox"";;name:=""Edit"";;index:=""16""");
	public static UIControls.Description RA_Field_2 = UIControls.Description.Create(@"windowtype:=""textbox"";;name:=""Edit"";;index:=""15""");
	public static UIControls.Description RA_Field_3 = UIControls.Description.Create(@"windowtype:=""textbox"";;name:=""Edit"";;index:=""14""");
	public static UIControls.Description DEC_Field_1 = UIControls.Description.Create(@"windowtype:=""textbox"";;name:=""Edit"";;index:=""12""");
	public static UIControls.Description DEC_Field_2 = UIControls.Description.Create(@"windowtype:=""textbox"";;name:=""Edit"";;index:=""11""");
	public static UIControls.Description DEC_Field_3 = UIControls.Description.Create(@"windowtype:=""textbox"";;name:=""Edit"";;index:=""10""");
	public static UIControls.Description Position_Button_OK = UIControls.Description.Create(@"name:=""Button"";;value:=""OK""");//Parent's name: Window_Position
}
#endregion

#region "Recorded code (SetPositionDriver) was generated via the recorder..."
public class SetPositionDriver: UIControls.InterAct{
	public SetPositionDriver(string[] ra, string[] dec) : base(System.IO.Path.GetDirectoryName(System.Windows.Forms.Application.ExecutablePath) + "\\" +
                System.IO.Path.GetFileNameWithoutExtension(System.Windows.Forms.Application.ExecutablePath) + ".stp", false)
    {
	//Recorded Code Below

		Window(SetPositionDescriptors.MainWindow).WinObject(SetPositionDescriptors.Main_Sub_Window).WinObject(@"name:=""Window"";;value:="""";;index:=""3""").Click(Mouse.AbsToRelativeCoordX(20246,1366),Mouse.AbsToRelativeCoordY(1451,768));
		Thread.Sleep(500);
		Window(SetPositionDescriptors.Window_Position).WinObject(SetPositionDescriptors.Equatorial_Coords_Frame).TextBox(SetPositionDescriptors.RA_Field_1).SetText("");
		Window(SetPositionDescriptors.Window_Position).WinObject(SetPositionDescriptors.Equatorial_Coords_Frame).TextBox(SetPositionDescriptors.RA_Field_1).AppendText(ra[0]);
		Window(SetPositionDescriptors.Window_Position).WinObject(SetPositionDescriptors.Equatorial_Coords_Frame).TextBox(SetPositionDescriptors.RA_Field_2).SetText("");
		Window(SetPositionDescriptors.Window_Position).WinObject(SetPositionDescriptors.Equatorial_Coords_Frame).TextBox(SetPositionDescriptors.RA_Field_2).AppendText(ra[1]);
		Window(SetPositionDescriptors.Window_Position).WinObject(SetPositionDescriptors.Equatorial_Coords_Frame).TextBox(SetPositionDescriptors.RA_Field_3).SetText("");
		Window(SetPositionDescriptors.Window_Position).WinObject(SetPositionDescriptors.Equatorial_Coords_Frame).TextBox(SetPositionDescriptors.RA_Field_3).AppendText(ra[2]);
		
		Window(SetPositionDescriptors.Window_Position).WinObject(SetPositionDescriptors.Equatorial_Coords_Frame).TextBox(SetPositionDescriptors.DEC_Field_1).SetText("");
		Window(SetPositionDescriptors.Window_Position).WinObject(SetPositionDescriptors.Equatorial_Coords_Frame).TextBox(SetPositionDescriptors.DEC_Field_1).AppendText(dec[0]);
		Window(SetPositionDescriptors.Window_Position).WinObject(SetPositionDescriptors.Equatorial_Coords_Frame).TextBox(SetPositionDescriptors.DEC_Field_1).SetText("");
		Window(SetPositionDescriptors.Window_Position).WinObject(SetPositionDescriptors.Equatorial_Coords_Frame).TextBox(SetPositionDescriptors.DEC_Field_1).AppendText(dec[1]);
		Window(SetPositionDescriptors.Window_Position).WinObject(SetPositionDescriptors.Equatorial_Coords_Frame).TextBox(SetPositionDescriptors.DEC_Field_1).SetText("");
		Window(SetPositionDescriptors.Window_Position).WinObject(SetPositionDescriptors.Equatorial_Coords_Frame).TextBox(SetPositionDescriptors.DEC_Field_1).AppendText(dec[2]);
		Window(SetPositionDescriptors.Window_Position).Button(SetPositionDescriptors.Position_Button_OK).Click(Mouse.AbsToRelativeCoordX(1727,1366),Mouse.AbsToRelativeCoordY(1365,768));
	}
}
#endregion

//****Add the following line of code into shared main(): 
//MakePdfDriver TestInstance_MakePdfDriver = new MakePdfDriver();

#region "Description (MakePdfDescriptors) was generated via the recorder..."
public class MakePdfDescriptors{
	//Description Code Below
	public static UIControls.Description MainWindow = UIControls.Description.Create(@"name:=""Window"";;value:=""Cartes du Ciel - Chart_1""");//Top level object
	public static UIControls.Description Main_Sub_Window = UIControls.Description.Create(@"name:=""Window"";;value:="""";;index:=""1""");//Parent's name: Window_Cartes_du_Ciel_HYPN_Chart_1
	public static UIControls.Description Window_Print_Chart = UIControls.Description.Create(@"name:=""Window"";;value:=""Print chart""");//Top level object
	public static UIControls.Description Button_Colour = UIControls.Description.Create(@"name:=""Button"";;value:=""Colour""");//Parent's name: Window_Print_chart
	public static UIControls.Description BW_LineMode_Radio = UIControls.Description.Create(@"name:=""Button"";;value:=""Black/white, line mode""");
	public static UIControls.Description Button_Print = UIControls.Description.Create(@"name:=""Button"";;value:=""Print""");//Parent's name: Window_Print_chart
	public static UIControls.Description Save_PDF_File_As = UIControls.Description.Create(@"name:=""#32770"";;value:=""Save PDF File As""");//Top level object
	public static UIControls.Description WorkerW = UIControls.Description.Create(@"name:=""WorkerW""");//Parent's name: NUM_32770_Save_PDF_File_As
	public static UIControls.Description Address_Band_Ro = UIControls.Description.Create(@"name:=""Address Band Root""");//Parent's name: WorkerW
	public static UIControls.Description Breadcrumb_Pare = UIControls.Description.Create(@"name:=""Breadcrumb Parent""");//Parent's name: Address_Band_Ro
	public static UIControls.Description SaveButton = UIControls.Description.Create(@"name:=""Button"";;value:=""&Save"""); //Parent's name: 
}
#endregion

#region "Recorded code (MakePdfDriver) was generated via the recorder..."
public class MakePdfDriver: UIControls.InterAct{
	public MakePdfDriver(string filePath) : base(System.IO.Path.GetDirectoryName(System.Windows.Forms.Application.ExecutablePath) + "\\" +
                System.IO.Path.GetFileNameWithoutExtension(System.Windows.Forms.Application.ExecutablePath) + ".stp", false)
    {
	//Recorded Code Below

		Window(MakePdfDescriptors.MainWindow).WinObject(MakePdfDescriptors.Main_Sub_Window).WinObject(@"name:=""Window"";;value:="""";;index:=""3""").Click(Mouse.AbsToRelativeCoordX(3934,1366),Mouse.AbsToRelativeCoordY(1024,768));
		Thread.Sleep(500);
		Window(MakePdfDescriptors.Window_Print_Chart).Button(MakePdfDescriptors.Button_Colour).RadioButton(MakePdfDescriptors.BW_LineMode_Radio).Click(Mouse.AbsToRelativeCoordX(2015,1366),Mouse.AbsToRelativeCoordY(853,768));
		Window(MakePdfDescriptors.Window_Print_Chart).Button(MakePdfDescriptors.Button_Print).Click(Mouse.AbsToRelativeCoordX(1391,1366),Mouse.AbsToRelativeCoordY(939,768));
		Thread.Sleep(1000);
		Window(MakePdfDescriptors.Save_PDF_File_As).WinObject(@"name:=""DUIViewWndClassName""").WinObject(@"name:=""DirectUIHWND"";;index:=""2""").WinObject(@"name:=""FloatNotifySink"";;index:=""3""").ComboBox(@"name:=""ComboBox"";;nearbylabel:=""File name:""").TextBox(@"name:=""Edit"";;controlid:=""1001""").SetText("");
		Window(MakePdfDescriptors.Save_PDF_File_As).WinObject(@"name:=""DUIViewWndClassName""").WinObject(@"name:=""DirectUIHWND"";;index:=""2""").WinObject(@"name:=""FloatNotifySink"";;index:=""3""").ComboBox(@"name:=""ComboBox"";;nearbylabel:=""File name:""").TextBox(@"name:=""Edit"";;controlid:=""1001""").AppendText(filePath);
		Window(MakePdfDescriptors.Save_PDF_File_As).Button(MakePdfDescriptors.SaveButton).SmartClick();
	}
}
#endregion