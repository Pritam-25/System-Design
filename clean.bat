@echo off
for /r %%f in (*.class) do del "%%f"
echo All .class files deleted.