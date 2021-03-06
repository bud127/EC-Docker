$[/myProject/scripts/preamble]

//// Input parameters
String serviceName = '$[serviceName]'
String serviceProjectName = '$[serviceProjectName]'
String applicationName = '$[applicationName]'
String clusterName = '$[clusterName]'
String envProjectName = '$[envProjectName]'
// default cluster project name if not explicitly set
if (!envProjectName) {
    envProjectName = serviceProjectName
}
String environmentName = '$[environmentName]'
String applicationRevisionId = '$[applicationRevisionId]'
String serviceEntityRevisionId = '$[serviceEntityRevisionId]'

def pluginProjectName = '$[/myProject/projectName]'

//// -- Driver script -- //
EFClient efClient = new EFClient()
// if cluster is not specified, find the cluster based on the environment that the application is mapped to.
if (!clusterName) {
	if (!applicationName) {
		efClient.handleError("'applicationName' must be specified if 'clusterName' is not specified in order to determine the cluster")
	}
	clusterName = efClient.getServiceCluster(serviceName,
			serviceProjectName,
			applicationName,
			applicationRevisionId,
			environmentName,
			envProjectName)
}

def clusterParameters = efClient.getProvisionClusterParameters(clusterName, envProjectName, environmentName)

def pluginConfig = efClient.getConfigValues('ec_plugin_cfgs', clusterParameters.config, pluginProjectName)

DockerClient dockerClient = new DockerClient(pluginConfig)

def clusterEndpoint = pluginConfig.endpoint

dockerClient.undeployService(
		efClient,
		clusterEndpoint,
		serviceName,
		serviceProjectName,
		applicationName,
		applicationRevisionId,
		clusterName,
		envProjectName,
		environmentName,
		serviceEntityRevisionId)

