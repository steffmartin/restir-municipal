#define MyAppName "SAART"
#define MyAppPublisher "JS Tecnologia em Processamento de Dados Eireli"
#define MyAppURL "http://www.jsconsult.com.br/"
#define MyAppExeName "SAART.jar"
#define MyAppVersion "0.0.1"
#define MyLicenseFile "C:\Users\steff\OneDrive\Documentos\Projetos\Restituição Municipal\Licença do Software.rtf"

[Setup]
AppId={{57FB9408-CEB7-4869-98B2-2621973A3917}
AppName={#MyAppName}
AppVersion={#MyAppVersion}
AppPublisher={#MyAppPublisher}
AppPublisherURL={#MyAppURL}
AppSupportURL={#MyAppURL}
AppUpdatesURL={#MyAppURL}
DefaultDirName={autopf}\JSConsult\{#MyAppName}
DefaultGroupName=Sistema {#MyAppName}
DisableProgramGroupPage=auto
AllowNoIcons=yes
LicenseFile={#MyLicenseFile}
PrivilegesRequired=admin
ArchitecturesInstallIn64BitMode=x64
MinVersion=0,6.1 
PrivilegesRequiredOverridesAllowed=commandline
OutputDir=C:\Users\steff\OneDrive\Documentos\Projetos\Restituição Municipal\Instalação
OutputBaseFilename=Instalador-{#MyAppName}-windows-x64
SetupIconFile=C:\Users\steff\Workspace\restir-municipal\setup\IconeInstalador.ico
UninstallDisplayIcon={app}\{#MyAppName}.ico
Compression=lzma
SolidCompression=yes
WizardStyle=modern
RestartIfNeededByRun=yes
UserInfoPage=yes
UsePreviousUserInfo=no

[Languages]
Name: "brazilianportuguese"; MessagesFile: "compiler:Languages\BrazilianPortuguese.isl"

[Tasks]
Name: "desktopicon"; Description: "{cm:CreateDesktopIcon}"; GroupDescription: "{cm:AdditionalIcons}"; Components: SistemaSAART

[Files]
Source: "{#MyLicenseFile}"; DestDir: "{app}"; Flags: ignoreversion; DestName: "Licença.rtf"; Components: SistemaSAART 
;Source: "C:\Users\steff\Workspace\restir-municipal\setup\config\*"; DestDir: "{app}\config\"; Flags: ignoreversion recursesubdirs createallsubdirs;  Components: SistemaSAART
Source: "IconeInstalador.ico"; DestDir: "{app}"; DestName: "{#MyAppName}.ico"; Flags: ignoreversion; Components: SistemaSAART
Source: "{tmp}\{#MyAppExeName}"; DestDir: "{app}"; Flags: external ignoreversion; ExternalSize: 110748; Components: SistemaSAART
Source: "{tmp}\postgresql.exe"; Flags: external dontcopy deleteafterinstall; ExternalSize: 324336; Components: PostgreSQL
Source: "{tmp}\java.exe"; Flags: external dontcopy deleteafterinstall; ExternalSize: 157290; Components: Java20
Source: "{tmp}\dbeaver.exe"; Flags: external dontcopy deleteafterinstall; ExternalSize: 115564; Components: DBeaverCommunity

[INI]
Filename: "{app}\config\application.properties"; Section: "DATABASE"; Key: "spring.datasource.url"; String: "jdbc:postgresql://localhost:5432/postgres"; Flags: uninsdeleteentry
Filename: "{app}\config\application.properties"; Section: "DATABASE"; Key: "spring.datasource.username"; String: "postgres"; Flags: uninsdeleteentry
Filename: "{app}\config\application.properties"; Section: "DATABASE"; Key: "spring.datasource.password"; String: "postgres"; Flags: uninsdeleteentry

[Icons]
Name: "{autodesktop}\{#MyAppName}"; Filename: "{cmd}"; WorkingDir: "{app}"; Flags: runminimized; IconFilename: "{app}\{#MyAppName}.ico"; Parameters: "/C start javaw {code:GetXmx} -jar ""{app}\{#MyAppName}.jar"""; Comment: "Iniciar SAART"; Components: SistemaSAART; Tasks: desktopicon
Name: "{group}\{#MyAppName}"; Filename: "{cmd}"; WorkingDir: "{app}"; Flags: runminimized; IconFilename: "{app}\{#MyAppName}.ico"; Parameters: "/C start javaw {code:GetXmx} -jar ""{app}\{#MyAppName}.jar"""; Comment: "Iniciar SAART"; Components: SistemaSAART
Name: "{group}\{#MyAppName} com Logs"; Filename: "{cmd}"; WorkingDir: "{app}"; Flags: runmaximized; IconFilename: "{app}\{#MyAppName}.ico"; Parameters: "/K java {code:GetXmx} -jar ""{app}\{#MyAppName}.jar"""; Comment: "Iniciar SAART"; Components: SistemaSAART
Name: "{group}\Licença de uso"; Filename: "{app}\Licença.rtf"; Components: SistemaSAART
Name: "{group}\{cm:ProgramOnTheWeb,{#MyAppName}}"; Filename: "{#MyAppURL}"; Components: SistemaSAART
Name: "{group}\{cm:UninstallProgram,{#MyAppName}}"; Filename: "{uninstallexe}"; Components: SistemaSAART

[Registry]
Root: "HKCU"; Subkey: "Environment"; ValueType: string; ValueName: "JAVA_HOME"; ValueData: "{commonpf}\Java\openjdk"; Components: Java20;
Root: "HKCU"; Subkey: "Environment"; ValueType: string; ValueName: "Path"; ValueData: "{olddata};{commonpf}\Java\openjdk\bin"; Components: Java20;
Root: "HKCU"; Subkey: "SOFTWARE\JavaSoft\Prefs\br\com\saart"; ValueName: "serialNumber"; ValueData: "{userinfoserial}"; ValueType: "string"; Components: SistemaSAART
Root: "HKCU"; Subkey: "SOFTWARE\JavaSoft\Prefs\br\com\saart"; ValueName: "organizacao"; ValueData: "{userinfoorg}"; ValueType: "string"; Components: SistemaSAART
Root: "HKCU"; Subkey: "SOFTWARE\JavaSoft\Prefs\br\com\saart"; Flags: dontcreatekey uninsdeletekey; Components: SistemaSAART

[Run]
Filename: "{tmp}\java.exe"; Parameters: "-o""{commonpf}\Java\openjdk"" -y -bd"; Flags: runhidden waituntilterminated; StatusMsg: "Instalando Open JDK"; Components: Java20
Filename: "{tmp}\postgresql.exe"; Parameters: "--unattendedmodeui minimal --mode unattended --disable-components ""pgAdmin,stackbuilder"" --locale ""pt-BR-x-icu"" --superaccount ""postgres"" --superpassword ""postgres"" --serverport 5432"; Flags: waituntilterminated; StatusMsg: "Instalando o banco de dados"; Components: PostgreSQL
Filename: "{tmp}\dbeaver.exe"; Parameters: "/allusers /S"; Flags: waituntilterminated; StatusMsg: "Instalando o DBeaver"; Components: DBeaverCommunity
Filename: "{cmd}"; Parameters: "/C start javaw {code:GetXmx} -jar ""{app}\{#MyAppName}.jar"""; WorkingDir: "{app}"; Flags: shellexec postinstall skipifsilent runminimized; Description: "{cm:LaunchProgram,{#StringChange(MyAppName, '&', '&&')}}"; Components: SistemaSAART

[UninstallDelete]
Type: filesandordirs; Name: "{app}"

[Components]
Name: "SistemaSAART"; Description: "Instala a aplicação principal do SAART"; ExtraDiskSpaceRequired: 113405952; Types: full custom compact; Flags: fixed
Name: "Java20"; Description: "Instalar o Java 20 gratuitamente (necessário)"; ExtraDiskSpaceRequired: 328454144; Types: compact full; Flags: restart; Check: InstallJava
Name: "PostgreSQL"; Description: "Instala o PostgreSQL 15 gratuitamente (necessário)"; ExtraDiskSpaceRequired: 340008960; Types: compact full; Check: InstallPostgreSQL
Name: "DBeaverCommunity"; Description: "Instalar o DBeaver Community gratuitamente (opcional)"; ExtraDiskSpaceRequired: 148824064; Types: full; Check: InstallDbeaver

[Code]
var
  DownloadPage: TDownloadWizardPage;
  Xmx: String;

function OnDownloadProgress(const Url, FileName: String; const Progress, ProgressMax: Int64): Boolean;
begin
  if Progress = ProgressMax then
    Log(Format('Successfully downloaded file to {tmp}: %s', [FileName]));
  Result := True;
end;

function PosExAny(const SubStr, S: string; StartPos: Integer): Integer;
var
  i: Integer;
begin
  for i := StartPos to Length(S) do
  begin
    if Pos(S[i], SubStr) > 0 then
    begin
      Result := i;
      Exit;
    end;
  end;
  Result := 0;  // Se nenhum caractere for encontrado
end;

function LastPosOfChar(const Ch: Char; const S: string): Integer;
var
  i: Integer;
begin
  Result := 0; // Inicializa com zero, indicando que não encontrou o caractere

  for i := Length(S) downto 1 do
  begin
    if S[i] = Ch then
    begin
      Result := i; // Atualiza a posição se o caractere for encontrado
      Exit;
    end;
  end;
end;   
  
procedure DecodeVersion(verstr: String; var verint: array of Integer);
var
  i, p: Integer;
  s, separators: string;
begin
  { initialize array }
  verint := [0, 0, 0, 0];
  i := 0;
  separators := '.-';

  while ((Length(verstr) > 0) and (i < 4)) do
  begin
    p := PosExAny(separators, verstr, 1);
    if p > 0 then
    begin
      s := Copy(verstr, 1, p - 1);
      verint[i] := StrToInt(s);
      i := i + 1;
      verstr := Copy(verstr, p + 1, Length(verstr));
    end
    else
    begin
      verint[i] := StrToInt(verstr);
      verstr := '';
    end;
  end;
end;

function CompareVersion (ver1, ver2: String) : Integer;
var
  verint1, verint2: array of Integer;
  i: integer;
begin
  SetArrayLength (verint1, 4);
  DecodeVersion (ver1, verint1);

  SetArrayLength (verint2, 4);
  DecodeVersion (ver2, verint2);

  Result := 0; i := 0;
  while ((Result = 0) and ( i < 4 )) do
  begin
    if verint1[i] > verint2[i] then
      Result := 1 
    else
      if verint1[i] < verint2[i] then
        Result := -1 
      else
        Result := 0;
    i := i + 1;
  end;
end;

function GetJavaVersionInfo(): String;
var
  ResultCode: Integer;
  OutputLines: TArrayOfString;
  FileName: String;
  FullCommand: String;
  OldState: Boolean;
  SpacePos: Integer;
begin
  Result := '0.0.0';
  FileName :=  ExpandConstant('{tmp}\javaVersion.txt');
  FullCommand := '/C java --version > ' + FileName;
  
  OldState := EnableFsRedirection(False);
  try
    if Exec(ExpandConstant('{cmd}'), FullCommand, '', SW_HIDE, ewWaitUntilTerminated, ResultCode) then
    begin
      if LoadStringsFromFile(FileName, OutputLines) then
      begin
        if Length(OutputLines) > 0 then
        begin
          SpacePos := Pos(' ', OutputLines[0]); //Pega posição do 1º espaço
          Delete(OutputLines[0], 1, SpacePos);  //Deleta o prefixo
          SpacePos := Pos(' ', OutputLines[0]); //Pega posição do próximo espaço
          Delete(OutputLines[0], SpacePos, Length(OutputLines[0])); //Deleta o sufixo
          Result := OutputLines[0]; //O que sobrou é a versão do java
        end;
      end;
    end;
  finally
    EnableFsRedirection(OldState);
  end;
end;

function GetPgVersionInfo(): String;
var
  ResultCode: Integer;
  OutputLines: TArrayOfString;
  FileName: String;
  FullCommand: String;
  OldState: Boolean;
  LastSpacePos: Integer;
begin
  Result := '0.0-0';
  FileName :=  ExpandConstant('{tmp}\pgVersion.txt');
  FullCommand := '/C postgres --version > ' + FileName;
  
  OldState := EnableFsRedirection(False);
  try
    if Exec(ExpandConstant('{cmd}'), FullCommand, '', SW_HIDE, ewWaitUntilTerminated, ResultCode) then
    begin
      if LoadStringsFromFile(FileName, OutputLines) then
      begin
        if Length(OutputLines) > 0 then
        begin
          LastSpacePos := LastPosOfChar(' ', OutputLines[0]); //Pega posição do último espaço
          Delete(OutputLines[0], 1, LastSpacePos);  //Deleta o prefixo
          Result := OutputLines[0]; //O que sobrou é a versão do postgresql
        end;
      end;
    end;
  finally
    EnableFsRedirection(OldState);
  end;
end;
     
function InstallJava(): Boolean;
begin
  Result := CompareVersion(GetJavaVersionInfo(), '20.0.0') < 0;
end;

function InstallPostgreSQL(): Boolean;
begin
  Result := CompareVersion(GetPgVersionInfo(), '15.0-0') < 0;
end;

function InstallDbeaver(): Boolean;
begin
  Result := not RegKeyExists(HKEY_CURRENT_USER, 'SOFTWARE\DBeaver');
end;

function NextButtonClick(CurPageID: Integer): Boolean;
begin
  if CurPageID = wpReady then begin
    DownloadPage.Clear;

    if IsComponentSelected('SistemaSAART') then
    begin
      DownloadPage.Add('https://onedrive.live.com/download?resid=AC0B91CB7D1F730D%2197495&authkey=!ACkxNlDrwA5LBjo', 'SAART.jar', '');
    end;

    if IsComponentSelected('PostgreSQL') then
    begin
      DownloadPage.Add('https://onedrive.live.com/download?resid=AC0B91CB7D1F730D%2197494&authkey=!AHuo2fgqpGDXyyI', 'postgresql.exe', '');
    end;

    if IsComponentSelected('Java20') then
    begin
      DownloadPage.Add('https://onedrive.live.com/download?resid=AC0B91CB7D1F730D%2197492&authkey=!ANo00QF1cQPRRxs', 'java.exe', '');
    end;

    if IsComponentSelected('DBeaverCommunity') then
    begin
      DownloadPage.Add('https://onedrive.live.com/download?resid=AC0B91CB7D1F730D%2197493&authkey=!AKkQyhGXBbMoz-w', 'dbeaver.exe', '');
    end;

    DownloadPage.Show;
    try
      try
        DownloadPage.Download; // This downloads the files to {tmp}
        Result := True;
      except
        if DownloadPage.AbortedByUser then
          Log('Aborted by user.')
        else
          SuppressibleMsgBox(AddPeriod(GetExceptionMessage), mbCriticalError, MB_OK, IDOK);
        Result := False;
      end;
    finally
      DownloadPage.Hide;
    end;
  end else
    Result := True;
end;

function ExtractNumerics(Lines: TArrayOfString): String;
var
  i, j: Integer;
begin
  Result := '';
  for i := 0 to High(Lines) do
  begin
    for j := 1 to Length(Lines[i]) do
    begin
      if (Lines[i][j] >= '0') and (Lines[i][j] <= '9') then
        Result := Result + Lines[i][j];
    end;
  end;
end;

function GetXmx(Value: string): string;
begin
  Result := Xmx;
end;

function CalcularXmx(Param: String): String;
var
  ResultCode: Integer;
  OutputLines: TArrayOfString;
  FileName: String;
  FirstCommand: String;
  FullCommand: String;
  OldState: Boolean;
  MemoriaUsavel: Int64;
  Percentual: Integer;
begin
  Result := '';
  FileName :=  ExpandConstant('{tmp}\memoria.txt');
  FirstCommand := '/C echo "" > ' + FileName;
  FullCommand := '/C wmic ComputerSystem get TotalPhysicalMemory >> ' + FileName;
  Percentual := StrToIntDef(Param, 25);
  
  OldState := EnableFsRedirection(False);
  try
    if Exec(ExpandConstant('{cmd}'), FirstCommand, '', SW_HIDE, ewWaitUntilTerminated, ResultCode) and Exec(ExpandConstant('{cmd}'), FullCommand, '', SW_HIDE, ewWaitUntilTerminated, ResultCode) then
    begin
      if LoadStringsFromFile(FileName, OutputLines) then
      begin
        if Length(OutputLines) > 0 then
        begin
          MemoriaUsavel := Round(StrToInt64Def(ExtractNumerics(OutputLines), 0) * Percentual / 100 / 1024 / 1024);
          if MemoriaUsavel > 0 then
          begin
            Result := '-Xmx' + IntToStr(MemoriaUsavel) + 'm';
          end;
        end;
      end;
    end;
  finally
    EnableFsRedirection(OldState);
  end;
end;

procedure InitializeWizard;
begin
  Xmx := CalcularXmx('75');
  DownloadPage := CreateDownloadPage(SetupMessage(msgWizardPreparing), SetupMessage(msgPreparingDesc), @OnDownloadProgress);
end;

function CheckSerial(Serial: String): Boolean;
begin
    Result := True;
end;