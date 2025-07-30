echo "this is the repo"
cd /D "C:\Users\wxpno\OneDrive\Documents\BTD6 Mod Sources\Dreadbloon-StS\"
copy /y * "G:\BTD6 Mod Sources\Dreadbloon-StS\"
copy /y .idea\* "G:\BTD6 Mod Sources\Dreadbloon-StS\"
copy /y .vscode\* "G:\BTD6 Mod Sources\Dreadbloon-StS\"
copy /y src\* "G:\BTD6 Mod Sources\Dreadbloon-StS\"

echo "this is the repo"
cd /D "C:\Users\wxpno\OneDrive\Documents\BTD6 Mod Sources\Dreadbloon-StS\target\classes"


echo "this points to a 7-Zip install"
"E:\Program Files\7-Zip\7z" a DreadbloonMod.jar dreadbloonsurv\
"E:\Program Files\7-Zip\7z" a DreadbloonMod.jar dreadbloonsurvResources\
"E:\Program Files\7-Zip\7z" a DreadbloonMod.jar ModTheSpire.json

echo "these point to a Steam install (and Slay The Spire)"
echo "For launching from Steam:"
copy /y DreadbloonMod.jar "E:\Program Files (x86)\Steam\steamapps\common\SlayTheSpire\mods\"

echo "For launching from a .bat file:"
copy /y DreadbloonMod.jar "E:\Program Files (x86)\Steam\steamapps\workshop\content\646570\1605060445\mods\"

echo "For launching from IntelliJ IDEA:"
copy /y DreadbloonMod.jar "C:\Users\wxpno\Documents\BTD6 Mod Sources\Dreadbloon-StS\mods"

timeout /t 1 >nul

