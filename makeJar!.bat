echo "this is the repo"
cd /D "C:\Users\wxpno\Documents\BTD6 Mod Sources\Dreadbloon-StS\target\classes"

echo "this points to a 7-Zip install"
"E:\Program Files\7-Zip\7z" a DreadbloonMod.jar dreadbloonsurv\
"E:\Program Files\7-Zip\7z" a DreadbloonMod.jar dreadbloonsurvResources\
"E:\Program Files\7-Zip\7z" a DreadbloonMod.jar ModTheSpire.json

echo "these point to a Steam install (and Slay The Spire)"
echo "copy /y DreadbloonMod.jar ^"E:\Program Files (x86)\Steam\steamapps\common\SlayTheSpire\mods\^""
copy /y DreadbloonMod.jar "E:\Program Files (x86)\Steam\steamapps\workshop\content\646570\1605060445\mods\"

cd /D "E:\Program Files (x86)\Steam\steamapps\workshop\content\646570\1605060445\"
"E:\Program Files (x86)\Steam\steamapps\common\SlayTheSpire\jre\bin\java.exe" -jar ModTheSpire.jar

