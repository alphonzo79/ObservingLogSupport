using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace GenerateStarCharts
{
    class Program
    {
        static string dataFile = "ic_61_catalog_database_values.xml";
        static string outputDirectoryRoot = @"C:\Users\joe.rowley\Documents\ObservingLog\ObservingLog\NonCodeResources\Catalogs\";
        static string outputRelativePath = @"ICCatalog\ICStarCharts\ic6\IC6Raw\";
        //static string outputDirectoryRoot = @"C:\Users\joe.rowley\Desktop\";
        //static string outputRelativePath = @"StarChartTest\";
        static void Main(string[] args)
        {
            FileOperations fileOps = new FileOperations(dataFile, outputDirectoryRoot + outputRelativePath);
            Dictionary<string, string[]> objectData = fileOps.readInFile();
            foreach (string name in objectData.Keys)
            {
                string ra = objectData[name][0];
                string[] raArray = fileOps.parseRa(ra);
                string dec = objectData[name][1];
                string[] decArray = fileOps.parseDec(dec);
                if (raArray.Length < 3 || decArray.Length < 3)
                    continue;

                SkyChartInteraction chartBuilder = new SkyChartInteraction();
                chartBuilder.createChart(raArray, decArray, outputDirectoryRoot + outputRelativePath + name);
            }
        }
    }

}
