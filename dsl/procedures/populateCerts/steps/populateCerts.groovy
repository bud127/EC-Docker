$[/myProject/scripts/preamble]

def pluginProjectName = '$[/myProject/projectName]'
// Input parameters
def configName = '$[config]'

EFClient efClient = new EFClient()
def pluginConfig = efClient.getConfigValues('ec_plugin_cfgs', configName, pluginProjectName)

def homeDir = System.getProperty('user.home')
def pathSeparator = File.separator
def certDir = "${homeDir}${pathSeparator}.docker${pathSeparator}cert"
new File(certDir).mkdirs()
File cacertFile = new File("${certDir}${pathSeparator}ca.pem")
cacertFile.text = pluginConfig.cacert

File clientcertFile = new File("${certDir}${pathSeparator}cert.pem")
clientcertFile.text = pluginConfig.cert

File clientkeyFile = new File("${certDir}${pathSeparator}key.pem")
clientkeyFile.text = pluginConfig.credential.password