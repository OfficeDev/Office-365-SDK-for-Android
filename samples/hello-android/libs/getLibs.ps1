$client = new-object System.Net.WebClient 
$shell_app = new-object -com shell.application
$scriptpath = $MyInvocation.MyCommand.Path
$dir = Split-Path $scriptpath
$filename = "guava-16.0.1.jar"

Write-Host "Downloading Google Guava 16.0.1"
$client.DownloadFile("http://search.maven.org/remotecontent?filepath=com/google/guava/guava/16.0.1/guava-16.0.1.jar", "$dir\$filename") 

