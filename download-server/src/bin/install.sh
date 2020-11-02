#!/bin/bash
#获取目录相关信息
relativePath=`dirname "$0"`
absolutePath=`cd "$relativePath"; pwd`
appHome=`cd $absolutePath/../; pwd`

#定义应用相关信息
appName="biu-download"
appMainJarName="app.jar"
appConfigFileName="application.yml"
if [[ $# -eq 1 ]];
then
        appName=$1
fi

#定义写入service文件变量
APP_NAME=$appName
WORK_DIR=$appHome
JRE_HOME=$appHome/jre
MAIN_JAR=$appHome/lib/$appMainJarName
CONFIG_FILE_PATH=$appHome/config/$appConfigFileName

chmod 744 $MAIN_JAR

#生成service文件并填写相关信息
tempServiceFile=$absolutePath/$appName.service

if [[ -f $tempServiceFile ]];
then
        rm -fr $tempServiceFile
fi

cp $absolutePath/template.service $tempServiceFile

sed -i 's!{APP_NAME}!'"${APP_NAME}"'!g' $tempServiceFile
sed -i 's!{WORK_DIR}!'"${WORK_DIR}"'!g' $tempServiceFile
sed -i 's!{JRE_HOME}!'"${JRE_HOME}"'!g' $tempServiceFile
sed -i 's!{MAIN_JAR}!'"${MAIN_JAR}"'!g' $tempServiceFile
sed -i 's!{CONFIG_FILE_PATH}!'"${CONFIG_FILE_PATH}"'!g' $tempServiceFile

#将生成的service文件移动到系统服务中
serviceFile="/lib/systemd/system/$appName.service"
if [[ -f $serviceFile ]];
then
        rm -fr $serviceFile
fi

mv $tempServiceFile $serviceFile

#刷新系统service服务，并启动该服务
sudo systemctl daemon-reload

systemctl start $appName.service

Systemctl status $appName.service
