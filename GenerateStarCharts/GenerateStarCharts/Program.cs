using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GenerateStarCharts
{
    class Program
    {
        static void Main(string[] args)
        {
            FileOperations fileOps = new FileOperations("ngc_1_catalog_database_values.xml", @"C:\Users\joe.rowley\Documents\ObservingLog\ObservingLog\NonCodeResources\Catalogs\NGCCatalog\NGCStarCharts\NGCRaw");
            fileOps.readInFile();
        }
    }
}
