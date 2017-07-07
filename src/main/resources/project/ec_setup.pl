#
#  Copyright 2015 Electric Cloud, Inc.
#
#  Licensed under the Apache License, Version 2.0 (the "License");
#  you may not use this file except in compliance with the License.
#  You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
#  Unless required by applicable law or agreed to in writing, software
#  distributed under the License is distributed on an "AS IS" BASIS,
#  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
#  See the License for the specific language governing permissions and
#  limitations under the License.
#

if ($promoteAction eq "promote") {
	$batch->deleteProperty("/server/ec_customEditors/pickerStep/Pull Docker image");
    $batch->deleteProperty("/server/ec_customEditors/pickerStep/Build Docker image");
    $batch->deleteProperty("/server/ec_customEditors/pickerStep/Run Docker container");
	$batch->deleteProperty("/server/ec_customEditors/pickerStep/Discover");
}

# Data that drives the create step picker registration for this plugin.
my %pull = (
    label       => "Pull Docker image",
    procedure   => "runDockerPull",
    description => "Performs a docker pull on the requested image",
    category    => "Resource Management"
);

my %build = (
    label       => "Build Docker image",
    procedure   => "runDockerBuild",
    description => "Performs a docker build",
    category    => "Resource Management"
);

my %run = (
    label       => "Run Docker container",
    procedure   => "runDockerRun",
    description => "Performs a container to run",
    category    => "Resource Management"
);

my %discover = (
    label       => "Discover",
    procedure   => "Discover",
    description => "Discovers services in Docker Compose file and creates corresponding application models for them in ElectricFlow",
    category    => "Resource Management"
);

@::createStepPickerSteps = (\%pull, \%build, \%run, \%discover);
