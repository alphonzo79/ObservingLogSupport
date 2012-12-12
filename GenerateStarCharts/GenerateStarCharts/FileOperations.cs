using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GenerateStarCharts
{
    class FileOperations
    {
        private string filename;
        private string outputDirectory;
        private string root = @"C:\Users\joe.rowley\Documents\ObservingLog\ObservingLog\res\values\";

        public FileOperations(string filename, string outputDirectory)
        {
            this.filename = filename;
            this.outputDirectory = outputDirectory;
        }

        public Dictionary<string, string[]> readInFile()
        {
            Dictionary<string, string[]> objectValues = new Dictionary<string, string[]>();

            //Read in the file string
            string text = System.IO.File.ReadAllText(root + filename);
            int firstIndex = text.IndexOf("catalog_default_values\">\"") + "catalog_default_values\">\"".Length;
            int lastIndex = text.IndexOf("\"</string>");
            text = text.Substring(firstIndex, lastIndex - firstIndex);

            //Parse it out into lines
            string[] lines = text.Split('\n');

            //extract objectName, RA and Dec
            foreach(string line in lines)
            {
                string[] values = line.Split(';');
                string imageResource = values[14];
                string[] imageSplit = imageResource.Split('/');
                string imgRef = imageSplit[3];
                string[] imageRefSplit = imgRef.Split('.');
                string objRef = imageRefSplit[0];
                string ra = values[9];
                string dec = values[10];
                string decUnescaped = dec.Replace("\\", "");
                Console.Out.WriteLine(decUnescaped);
                objectValues.Add(objRef, new string[]{ra, decUnescaped});
            }

            return objectValues;
        }
    }
}
