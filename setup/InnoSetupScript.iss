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
ShowLanguageDialog=no
UninstallDisplayName=Sistema SAART

[Files]
;APP
Source: "{tmp}\{#MyAppExeName}"; DestDir: "{app}"; Flags: external ignoreversion; ExternalSize: 129589248
Source: "IconeInstalador.ico"; DestDir: "{app}"; DestName: "{#MyAppName}.ico"; Flags: ignoreversion
Source: "{#MyLicenseFile}"; DestDir: "{app}"; DestName: "Licença.rtf"; Flags: ignoreversion
Source: "restart.cmd"; DestDir: "{app}"; Flags: ignoreversion
;JAVA
Source: "{tmp}\java.exe"; Flags: external dontcopy deleteafterinstall; ExternalSize: 161067008; Components: Java20
;DBEAVER
Source: "{tmp}\dbeaver.exe"; Flags: external dontcopy deleteafterinstall; ExternalSize: 118337536; Components: DBeaverCommunity
Source: "{tmp}\dbeaverdata.exe"; Flags: external dontcopy deleteafterinstall; ExternalSize: 3809280; Components: DBeaverCommunity
;POSTGRESQL
Source: "{tmp}\postgresql.exe"; Flags: external dontcopy deleteafterinstall; ExternalSize: 332120064; Components: PostgreSQL

[Icons]
;DESKTOP
Name: "{autodesktop}\{#MyAppName}"; Filename: "{cmd}"; WorkingDir: "{app}"; Flags: runminimized; IconFilename: "{app}\{#MyAppName}.ico"; IconIndex: 0; Parameters: "/C start javaw {code:GetXmx} -jar ""{app}\{#MyAppExeName}"""; Comment: "Iniciar SAART"; Tasks: desktopiconSAART; AfterInstall: SetElevationBit('{autodesktop}\{#MyAppName}.lnk')
Name: "{autodesktop}\DBeaver"; Filename: "{commonpf}\DBeaver\dbeaver.exe"; Flags: uninsneveruninstall; Comment: "Abrir o DBeaver Community"; Tasks: desktopiconDBeaver
;START MENU
Name: "{group}\{#MyAppName}"; Filename: "{cmd}"; WorkingDir: "{app}"; Flags: runminimized; IconFilename: "{app}\{#MyAppName}.ico"; IconIndex: 0; Parameters: "/C start javaw {code:GetXmx} -jar ""{app}\{#MyAppExeName}"""; Comment: "Iniciar SAART"; AfterInstall: SetElevationBit('{group}\{#MyAppName}.lnk')
Name: "{group}\{#MyAppName} com Logs"; Filename: "{cmd}"; WorkingDir: "{app}"; Flags: runmaximized; IconFilename: "{app}\{#MyAppName}.ico"; IconIndex: 0; Parameters: "/K java {code:GetXmx} -jar ""{app}\{#MyAppExeName}"""; Comment: "Iniciar SAART com janela de Logs"; AfterInstall: SetElevationBit('{group}\{#MyAppName} com Logs.lnk')
Name: "{group}\Licença de uso"; Filename: "{app}\Licença.rtf"; Comment: "Ler os termos de uso do sistema"
Name: "{group}\{cm:ProgramOnTheWeb,{#MyAppName}}"; Filename: "{#MyAppURL}"; Comment: "Acessar o site da desenvolvedora do sistema na WEB"
Name: "{group}\{cm:UninstallProgram,{#MyAppName}}"; Filename: "{uninstallexe}"; Comment: "Desinstalar o sistema e todos os seus componentes"
;PASTA APP
Name: "{app}\{#MyAppName}"; Filename: "{cmd}"; WorkingDir: "{app}"; Flags: runminimized; IconFilename: "{app}\{#MyAppName}.ico"; IconIndex: 0; Parameters: "/C start javaw {code:GetXmx} -jar ""{#MyAppExeName}"""; Comment: "Iniciar SAART"; AfterInstall: SetElevationBit('{app}\{#MyAppName}.lnk')
Name: "{app}\restart"; Filename: "{cmd}"; WorkingDir: "{app}"; Flags: runminimized; Parameters: "/C cd ""{app}"" && restart.cmd"; Comment: "Atualizar e reiniciar o SAART"; AfterInstall: SetElevationBit('{app}\restart.lnk')

[INI]
;config\application.properties [GERAL]
Filename: "{app}\config\application.properties"; Section: "GERAL"; Key: "spring.application.install-dir"; String: "{code:EscapeBars|{app}}";  Flags: uninsdeleteentry
;config\application.properties [DATABASE]
Filename: "{app}\config\application.properties"; Section: "DATABASE"; Key: "spring.datasource.url"; String: "jdbc:postgresql://localhost:5432/postgres"; Flags: uninsdeleteentry
Filename: "{app}\config\application.properties"; Section: "DATABASE"; Key: "spring.datasource.username"; String: "postgres"; Flags: uninsdeleteentry
Filename: "{app}\config\application.properties"; Section: "DATABASE"; Key: "spring.datasource.password"; String: "postgres"; Flags: uninsdeleteentry

[Registry]
;APP
Root: "HKCU"; Subkey: "SOFTWARE\JavaSoft\Prefs\br\com\saart"; ValueName: "serialNumber"; ValueData: "{userinfoserial}"; ValueType: "string"
Root: "HKCU"; Subkey: "SOFTWARE\JavaSoft\Prefs\br\com\saart"; ValueName: "organizacao"; ValueData: "{userinfoorg}"; ValueType: "string"
;JAVA
Root: "HKCU"; Subkey: "Environment"; ValueType: string; ValueName: "JAVA_HOME"; ValueData: "{commonpf}\Open JDK 20"; Components: Java20;
Root: "HKCU"; Subkey: "Environment"; ValueType: string; ValueName: "Path"; ValueData: "{olddata};{commonpf}\Open JDK 20\bin"; Components: Java20;
Root: "HKCU"; Subkey: "SOFTWARE\JavaSoft\Prefs\br\com\saart"; ValueName: "Java20"; ValueType: "string"; ValueData: ""; Components: Java20
;POSTGRESQL
Root: "HKCU"; Subkey: "SOFTWARE\JavaSoft\Prefs\br\com\saart"; ValueName: "PostgreSQL"; ValueType: "string"; ValueData: ""; Components: PostgreSQL
;DBEAVER
Root: "HKCU"; Subkey: "SOFTWARE\JavaSoft\Prefs\br\com\saart"; ValueName: "DBeaverCommunity"; ValueType: "string"; ValueData: ""; Components: DBeaverCommunity

[Components]
Name: "SistemaSAART"; Description: "Instalar a aplicação principal do SAART"; ExtraDiskSpaceRequired: 129589248; Types: full custom compact; Flags: fixed
Name: "Java20"; Description: "Instalar o Open JDK Java 20 gratuitamente (necessário)"; ExtraDiskSpaceRequired: 328454144; Types: compact full; Flags: restart disablenouninstallwarning; Check: InstallJava
Name: "PostgreSQL"; Description: "Instalar o PostgreSQL 15 gratuitamente (necessário)"; ExtraDiskSpaceRequired: 340008960; Types: compact full; Flags: disablenouninstallwarning; Check: InstallPostgreSQL
Name: "DBeaverCommunity"; Description: "Instalar o DBeaver Community gratuitamente (opcional)"; ExtraDiskSpaceRequired: 155156480; Types: full; Flags: disablenouninstallwarning; Check: InstallDbeaver

[Tasks]
Name: "desktopiconSAART"; Description: "Sistema SAART"; GroupDescription: "Criar ícones na área de trabalho para:"
Name: "desktopiconDBeaver"; Description: "DBeaver Community"; GroupDescription: "Criar ícones na área de trabalho para:"; Components: DBeaverCommunity

[Languages]
Name: "brazilianportuguese"; MessagesFile: "compiler:Languages\BrazilianPortuguese.isl"

[Run]
;JAVA
Filename: "{tmp}\java.exe"; Parameters: "-o""{commonpf}\Open JDK 20"" -y -bd"; Flags: runhidden waituntilterminated; StatusMsg: "Instalando Open JDK Java 20"; Components: Java20
;POSTGRESQL
Filename: "{tmp}\postgresql.exe"; Parameters: "--unattendedmodeui minimal --mode unattended --disable-components ""pgAdmin,stackbuilder"" --superaccount ""postgres"" --superpassword ""postgres"" --serverport 5432"; Flags: waituntilterminated; StatusMsg: "Instalando o banco de dados"; Components: PostgreSQL
;DBEAVER
Filename: "{tmp}\dbeaver.exe"; Parameters: "/allusers /S"; Flags: waituntilterminated; StatusMsg: "Instalando o DBeaver Community"; Components: DBeaverCommunity
Filename: "{tmp}\dbeaverdata.exe"; Parameters: "-o""{userappdata}"" -y -bd"; Flags: runhidden waituntilterminated; StatusMsg: "Configurando o DBeaver Community"; Components: DBeaverCommunity
;dbeaver-cli.exe -nosplash -q -con "name=postgres3|driver=postgres-jdbc|url=jdbc:postgresql://localhost:5432/postgres|host=localhost|port=5432|database=postgres|user=postgres|password=postgres|auth=native|savePassword=true|connect=false|create=true|save=true"
;dbeaver-cli.exe --quit
;POST INSTALL
Filename: "{cmd}"; Parameters: "/C start javaw {code:GetXmx} -jar ""{app}\{#MyAppExeName}"""; WorkingDir: "{app}"; Flags: shellexec postinstall skipifsilent runhidden; Description: "{cm:LaunchProgram,{#StringChange(MyAppName, '&', '&&')}}"
Filename: "{commonpf}\DBeaver\dbeaver.exe"; Flags: postinstall skipifsilent unchecked; Description: "{cm:LaunchProgram,DBeaver Community}"; Components: DBeaverCommunity

[UninstallDelete]
Type: filesandordirs; Name: "{app}"

[Code]
var
  DownloadPage: TDownloadWizardPage;
  Xmx: String;

function EscapeBars(Param: String): String;
begin
  StringChangeEx(Param, '\', '\\', False);
  Result := Param;
end;

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

function UninstallJava(): Boolean;
  var S: String;
begin
  Result := RegQueryStringValue(HKEY_CURRENT_USER, 'SOFTWARE\JavaSoft\Prefs\br\com\saart', 'Java20', S);
end;

function InstallPostgreSQL(): Boolean;
begin
  Result := CompareVersion(GetPgVersionInfo(), '15.0-0') < 0;
end;

function UninstallPostgreSQL(): Boolean;
  var S: String;
begin
  Result := RegQueryStringValue(HKEY_CURRENT_USER, 'SOFTWARE\JavaSoft\Prefs\br\com\saart', 'PostgreSQL', S);
end;

function InstallDbeaver(): Boolean;
begin
  Result := not RegKeyExists(HKEY_CURRENT_USER, 'SOFTWARE\DBeaver');
end;

function UninstallDbeaver(): Boolean;
  var S: String;
begin
  Result := RegQueryStringValue(HKEY_CURRENT_USER, 'SOFTWARE\JavaSoft\Prefs\br\com\saart', 'DBeaverCommunity', S);
end;

function NextButtonClick(CurPageID: Integer): Boolean;
begin
  if CurPageID = wpReady then begin
    DownloadPage.Clear;

    if IsComponentSelected('SistemaSAART') then
    begin
      DownloadPage.Add('https://onedrive.live.com/download?resid=AC0B91CB7D1F730D%2197715&authkey=!AJ2XL1Y3leL-HzQ', 'SAART.jar', '');
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
      DownloadPage.Add('https://onedrive.live.com/download?resid=AC0B91CB7D1F730D%2197620&authkey=!AKmoi21U45PSSuM', 'dbeaverdata.exe', '');
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
    Result := Length(Trim(Serial)) > 0;
end;

procedure CurUninstallStepChanged(CurUninstallStep: TUninstallStep);
var
  OldState: Boolean;
  ResultCode: Integer;
  WizardForm: TSetupForm;
  StatusMemo: TMemo;
  Exibido: Boolean;
begin
  WizardForm := CreateCustomForm;
  WizardForm.Caption := 'Desinstalação de componentes';
  WizardForm.ClientWidth := ScaleX(500);
  WizardForm.ClientHeight := ScaleY(200);

  StatusMemo := TMemo.Create(WizardForm);
  StatusMemo.Parent := WizardForm;
  StatusMemo.Align := alClient;
  StatusMemo.ScrollBars := ssVertical;
  StatusMemo.ReadOnly := True;

  Exibido := False;

  if CurUninstallStep = usPostUninstall then
  begin
    if UninstallDbeaver() then
    begin
      if MsgBox('Você também deseja desinstalar o Dbeaver Community?', mbConfirmation, MB_YESNO) = IDYES then
      begin
        if not Exibido then
        begin
          WizardForm.Show;
        end;
        OldState := EnableFsRedirection(False);
        try
          StatusMemo.Lines.Add('Desinstalando o Dbeaver Community, aguarde...');
          if Exec(ExpandConstant('{commonpf}\DBeaver\Uninstall.exe'), '/allusers /S', '', SW_HIDE, ewWaitUntilTerminated, ResultCode) then
          begin
            DelTree(ExpandConstant('{userappdata}\DBeaverData'), True, True, True);
            DeleteFile(ExpandConstant('{autodesktop}\DBeaver'));
            StatusMemo.Lines.Add('DBeaver Community foi desinstalado com sucesso.');
          end
          else
          begin
            StatusMemo.Lines.Add('Erro ao desinstalar o DBeaver Community.');
          end;
        finally
          EnableFsRedirection(OldState);
        end;
      end;
    end;

    if UninstallPostgreSQL() then
    begin
      if MsgBox('Você também deseja desinstalar o PostgreSQL 15?', mbConfirmation, MB_YESNO) = IDYES then
      begin
        if not Exibido then
        begin
          WizardForm.Show;
        end;
        OldState := EnableFsRedirection(False);
        try
          StatusMemo.Lines.Add('Desinstalando o PostgreSQL 15, aguarde...');
          if Exec(ExpandConstant('{commonpf}\PostgreSQL\15\uninstall-postgresql.exe'), '--mode unattended --unattendedmodeui none', '', SW_HIDE, ewWaitUntilTerminated, ResultCode) then
          begin
            StatusMemo.Lines.Add('PostgreSQL 15 foi desinstalado com sucesso.');
            if MsgBox('Você também deseja apagar completamente a pasta da base de dados?', mbConfirmation, MB_YESNO) = IDYES then
            begin
              StatusMemo.Lines.Add('Excluindo a pasta da base de dados, aguarde...');
              if DelTree(ExpandConstant('{commonpf}\PostgreSQL\15\data'), True, True, True) then
              begin
                StatusMemo.Lines.Add('Base de dados excluída com sucesso.');
              end
              else
              begin
                StatusMemo.Lines.Add('Erro ao excluir a pasta da base de dados.');
              end;
            end;
          end
          else
          begin
            StatusMemo.Lines.Add('Erro ao desinstalar o PostgreSQL 15.');
          end;
        finally
          EnableFsRedirection(OldState);
        end;
      end;
    end;

    if UninstallJava() then
    begin
      if MsgBox('Você também deseja desinstalar o Open JDK Java 20?', mbConfirmation, MB_YESNO) = IDYES then
      begin
        if not Exibido then
        begin
          WizardForm.Show;
        end;
        OldState := EnableFsRedirection(False);
        try
          StatusMemo.Lines.Add('Desinstalando o Open JDK Java 20, aguarde...');
          if DelTree(ExpandConstant('{commonpf}\Open JDK 20'), True, True, True) then
          begin
            StatusMemo.Lines.Add('Open JDK Java 20 foi desinstalado com sucesso.');
          end
          else
          begin
            StatusMemo.Lines.Add('Erro ao desinstalar o Open JDK Java 20.');
          end;
        finally
          EnableFsRedirection(OldState);
        end;
      end;
    end;

    if Exibido then
    begin
      WizardForm.Hide;
    end;

    RegDeleteKeyIncludingSubkeys(HKEY_CURRENT_USER, 'SOFTWARE\JavaSoft\Prefs\br\com\saart');
    RegDeleteKeyIfEmpty(HKEY_CURRENT_USER, 'SOFTWARE\JavaSoft\Prefs\br\com');
    RegDeleteKeyIfEmpty(HKEY_CURRENT_USER, 'SOFTWARE\JavaSoft\Prefs\br');
  end;
end;

procedure SetElevationBit(Filename: string);
var
  Buffer: string;
  Stream: TStream;
begin
  Filename := ExpandConstant(Filename);
  Log('Setting elevation bit for ' + Filename);

  Stream := TFileStream.Create(FileName, fmOpenReadWrite);
  try
    Stream.Seek(21, soFromBeginning);
    SetLength(Buffer, 1);
    Stream.ReadBuffer(Buffer, 1);
    Buffer[1] := Chr(Ord(Buffer[1]) or $20);
    Stream.Seek(-1, soFromCurrent);
    Stream.WriteBuffer(Buffer, 1);
  finally
    Stream.Free;
  end;
end;