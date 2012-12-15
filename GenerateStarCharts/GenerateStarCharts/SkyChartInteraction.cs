using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;

namespace GenerateStarCharts
{
    class SkyChartInteraction
    {
        private string exeLocation = @"C:\Users\joe.rowley\Documents\ObservingLog\ObservingLogSupport\GenerateStarCharts\SlickTestChartCommander\StarChartDriver\StarChartDriver.exe";
        public void createChart(string[] ra, string[] dec, string outputPath)
        {
            StringBuilder sb = new StringBuilder();
            sb.Append(ra[0]);
            sb.Append(", ");
            sb.Append(ra[1]);
            sb.Append(", ");
            sb.Append(ra[2]);
            sb.Append(", ");
            sb.Append(dec[0]);
            sb.Append(", ");
            sb.Append(dec[1]);
            sb.Append(", ");
            sb.Append(dec[2]);
            sb.Append(", ");
            sb.Append(outputPath);
            System.Diagnostics.Process.Start(exeLocation, sb.ToString());
            Thread.Sleep(20000);
        }
    }
}
