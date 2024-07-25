@echo off
setlocal enabledelayedexpansion

:menu
echo ��ѡ��һ������:
echo 1. ��Ӷ���
echo 2. ��ȡ���ж���
echo 3. ���¶�����Ϣ
echo 4. ɾ������
echo 5. �˳�

set /p choice="������ѡ��Ĳ������: "

if "%choice%"=="1" goto add_animal
if "%choice%"=="2" goto get_animals
if "%choice%"=="3" goto update_animal
if "%choice%"=="4" goto delete_animal
if "%choice%"=="5" goto end

echo ѡ����Ч�������ԡ�
goto menu

:add_animal
set /p name="���붯������: "
set /p species="���붯������: "
set /p age="���붯������: "
set /p healthStatus="���붯�｡��״̬: "

curl -X POST http://192.168.10.3:8080/animals -H "Content-Type: application/json" -d "{\"name\":\"%name%\",\"species\":\"%species%\",\"age\":%age%,\"healthStatus\":\"%healthStatus%\"}"

goto menu

:get_animals
curl -s -X GET http://192.168.10.3:8080/animals
echo.
goto menu

:update_animal
set /p id="����Ҫ���µĶ���ID: "
set /p name="�����µĶ�������: "
set /p species="�����µĶ�������: "
set /p age="�����µĶ�������: "
set /p healthStatus="�����µĶ��｡��״̬: "

curl -X PUT http://192.168.10.3:8080/animals/%id% -H "Content-Type: application/json" -d "{\"name\":\"%name%\",\"species\":\"%species%\",\"age\":%age%,\"healthStatus\":\"%healthStatus%\"}"

goto menu

:delete_animal
set /p id="����Ҫɾ���Ķ���ID: "

curl -X DELETE http://192.168.10.3:8080/animals/%id%

goto menu

:end
echo ���������
endlocal
exit /b
