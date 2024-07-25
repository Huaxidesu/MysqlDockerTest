@echo off
setlocal enabledelayedexpansion

:menu
echo 请选择一个操作:
echo 1. 添加动物
echo 2. 获取所有动物
echo 3. 更新动物信息
echo 4. 删除动物
echo 5. 退出

set /p choice="请输入选择的操作编号: "

if "%choice%"=="1" goto add_animal
if "%choice%"=="2" goto get_animals
if "%choice%"=="3" goto update_animal
if "%choice%"=="4" goto delete_animal
if "%choice%"=="5" goto end

echo 选择无效，请重试。
goto menu

:add_animal
set /p name="输入动物名称: "
set /p species="输入动物物种: "
set /p age="输入动物年龄: "
set /p healthStatus="输入动物健康状态: "

curl -X POST http://192.168.10.3:8080/animals -H "Content-Type: application/json" -d "{\"name\":\"%name%\",\"species\":\"%species%\",\"age\":%age%,\"healthStatus\":\"%healthStatus%\"}"

goto menu

:get_animals
curl -s -X GET http://192.168.10.3:8080/animals
echo.
goto menu

:update_animal
set /p id="输入要更新的动物ID: "
set /p name="输入新的动物名称: "
set /p species="输入新的动物物种: "
set /p age="输入新的动物年龄: "
set /p healthStatus="输入新的动物健康状态: "

curl -X PUT http://192.168.10.3:8080/animals/%id% -H "Content-Type: application/json" -d "{\"name\":\"%name%\",\"species\":\"%species%\",\"age\":%age%,\"healthStatus\":\"%healthStatus%\"}"

goto menu

:delete_animal
set /p id="输入要删除的动物ID: "

curl -X DELETE http://192.168.10.3:8080/animals/%id%

goto menu

:end
echo 程序结束。
endlocal
exit /b
