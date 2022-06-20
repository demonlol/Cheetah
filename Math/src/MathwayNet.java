import java.awt.*;
import java.io.*;
import java.net.*;

public class MathwayNet {

	static {
		//TODO
		System.out.println("TODO: MATHWAY NETWORKING");
		try {
			Desktop.getDesktop().browse(URI.create("https://www.mathway.com/Algebra"));
		} catch (IOException e) { e.printStackTrace(); }
	}

	public static void OCR() {
		/*
Request URL: https://www.mathway.com/OCR
Request Method: POST
Status Code: 200

accept: * / *
accept-encoding: gzip, deflate, br
accept-language: en-US,en;q=0.9
content-length: 8550
content-type: application/x-www-form-urlencoded
cookie: Mathway.LastSubject=Algebra; Mathway.IncomingCulture=en-US; Mathway.Culture=en-US; usprivacy=1YNY; _ga=GA1.2.34571938.1649718565; Mathway.AnonUserId=794552748; sbm_country=US; Mathway.StaticAdsTest={%22testId%22:33%2C%22testVariantId%22:154}; Mathway.AnonymousPurchasing={%22testId%22:29%2C%22testVariantId%22:133}; Mathway.CreditPackPurchasingTestVariant={%22testId%22:18%2C%22testVariantId%22:129}; Mathway.UpgradeGuaranteeTestVariant={%22testId%22:22%2C%22testVariantId%22:121}; Mathway.QuarterlyTestVariant={%22testId%22:12%2C%22testVariantId%22:120}; Mathway.OneOffPurchaseTestVariant={%22testId%22:13%2C%22testVariantId%22:101}; Mathway.PlancodeTestVariant={%22testId%22:1%2C%22testVariantId%22:116}; _pbjs_userid_consent_data=3524755945110770; _pubcid=9d89a509-c582-4620-87e8-5be0ff34ef70; __gads=ID=ccfac60a85addd8b:T=1649718566:S=ALNI_MYTvTI1kaEsGjFwnBGbvfs-20XoqA; _lr_env_src_ats=false; pbjs-unifiedid=%7B%22TDID%22%3A%220ce9c866-8a89-4a02-b328-63dff6a864f8%22%2C%22TDID_LOOKUP%22%3A%22TRUE%22%2C%22TDID_CREATED_AT%22%3A%222022-03-11T23%3A09%3A27%22%7D; _gcl_au=1.1.79878581.1649718576; _fbp=fb.1.1649718575695.1766408596; _scid=16e7b9fa-3428-40f4-838e-528fa6420950; al_cell=edge-34-test; Mathway.Location=US; Mathway.GDPR=1; CVID=df335246-0984-4f9c-958e-1f2f9a6747f3; local_fallback_mcid=05457024589928240433100171871469060092; mcid=05457024589928240433100171871469060092; s_ecid=MCMID|05457024589928240433100171871469060092; _gid=GA1.2.1732146773.1650947382; apay-session-set=5dLexyaZfliFPaGVcxDD9C%2FqoRSXxez2bP%2FBwE%2B%2BTt2LzobfDCPWcIAWJjxpYjw%3D; language=en_US; amazon-pay-connectedAuth=connectedAuth_general; __gpi=UID=0000048cbcf64cc5:T=1650019843:RT=1650947381:S=ALNI_Mbsn2y9XzwY7tbpYSmYDRGwY86Q6Q; idl_env=AmnVSCswCnfTWqRzC6Fh1hnHYTD26C6SgyGDXxtCUPxl90zGpAzxw_Pc8LIJu39BAWj1kYgb0irb0yanZjAobZjJTWYvSb7lmBbb; IR_gbd=mathway.com; _sctr=1|1650862800000; CSID=1650959375972; OptanonConsent=isIABGlobal=false&datestamp=Tue+Apr+26+2022+02%3A50%3A58+GMT-0500+(Central+Daylight+Time)&version=6.13.0&hosts=&consentId=129be9da-4d46-4a10-800d-2d337bc235a2&interactionCount=1&landingPath=NotLandingPage&groups=snc%3A1%2Cfnc%3A1%2Cprf%3A1%2CSPD_BG%3A1%2Ctrg%3A1%2Cgoog%3A1&AwaitingReconsent=false; sbm_a_b_test=34-test; _awl=2.1650959458.0.5-fce18d318e7249707f84bed1f5996cdd-6763652d75732d6561737431-0; IR_14422=1650959460074%7C0%7C1650959460074%7C%7C; _gat=1
origin: https://www.mathway.com
referer: https://www.mathway.com/Algebra
sec-ch-ua: " Not A;Brand";v="99", "Chromium";v="100", "Google Chrome";v="100"
sec-ch-ua-mobile: ?0
sec-ch-ua-platform: "Windows"
sec-fetch-dest: empty
sec-fetch-mode: cors
sec-fetch-site: same-origin
user-agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/100.0.4896.127 Safari/537.36
x-requested-with: XMLHttpRequest

imageData=data%3Aimage%2Fpng%3Bbase64%2CiVBORw0KGgoAAAANSUhEUgAAAIIAAAAqCAYAAAB%2FTIN%2BAAAAAXNSR0IArs4c6QAAFzJJREFUeF7tWwusZVV5%2FtZa%2B3XOvXfuAFNBrGlDqxiRWmmCUBFUBhhgeMoUBIIyvEZAEQesICWpUUSkQkGxlgpSBLWoQRQasPIQC1aTRqvVqG1SWkR5DTP3zj3n7L3Xo%2Fn%2Bf5%2B5A9UiA3eCePckc885%2B73%2B7%2F%2F%2Bt9l5550TFrff%2BhUwi0D4rceALMAiEBZxsAiERQzMr8AiIyyiYZERFjGwyAiLGHjKCiyahkVILJqGRQxsFdNgwEyV%2Fh%2B7O%2FKbgYGV70n2AVb%2BJMDE7hez6Uxegd900%2BskY%2BRwPQ%2BI%2FC5nRDlWf54%2FyyT9zD%2B853jPOJOmz%2Fjkc%2Ba%2F83jdr5%2FG75Bgu%2BeJ3fvM33%2F%2B3uPryBVM0teUf%2FxlPpc3f5fNn3zTiy%2F4hwUyDZsvGJepkUWIcEjIYGKuArRBFiQLKsRkWySTkJJDMhZcau6x0erCmUb2B5OBwi0CgWHQWCfXd8nDpCRAkYU1%2FNnARSf385b7EkwaA4rX55FBZGTgBC08RiGsAuN1eVw0GQIcgrHg0%2BWxkf2tzUWkGe8vUNPnFlgLaK2CsANCFvW6BnpdHs9n5nUVL%2FNgXXAEjFVg4TKLfFW%2B2OZAyJAShUjBGQQCIUUUHRCi9U8CAs80ycIlAiEimQaxA4JNBmUHhNoSOBREK8fNayjXmdfIVDA2UJzKGkoqIghqtsi%2BAwK%2FUBQErkVAnho5rjUlgnFyBZ6TR97vqUCg%2BAkTBSI%2F8x2UuQh8wHVASCZ0rMD9Y355wTCCUrysAhdWXr3TwE5TVKjUbN2bB4pORaTarIJIcKKTLimNUpA8hwJyCSgCF5aMkIlQHdpOc7n4BAafw6K1Raex8%2FujaKjcEY6PJ0ww1kg%2BHyGQiXjKOBBB1rYvQLAg8yigVMCq4VaYpntWwzOdArljtGS8rA1NGs9prJo57uc7uu587tvanLAApoFAINJJcZkIgosullE0IwI0AdRcw4UCXCIzKHBUk6hV%2Bt3KAgUBjrekT10kLlwR9bzW5GoGSLWizWoSsqi021oywpi6IRQsJoiCoS5GPodqsHxPCgRvMvlcxmEHhJ6c6zrmGZsPClO8GgGHglwZRdkvJxB4TUsgKoC8MagzsotFFoE80tSoqeA9xn7Pb7BpmAcChJJVy%2BYZggsy0gXpKFs108CILadASOEKBNUU%2BgZAY%2FPOjqr25DGAJiKYvGMT2hj1OwRYib4IEaILDJqkDmgCyA4IKRXdcypDKCepaRAfprP9Y7OgsO6ETuNh1PSIj9IBQa9EINjO9JHRvCgAV0SA4ByiscgDBLR554u8cICwyRtWp0mFwM%2B05QTKqKNkUrpBS64UiiRr0IlUJ9DETATuMBIg1LYSahYRpYAskqK54IVoUJDzCA614gFl5%2BzV8jegEComc4iDCAVcMDQdZB8V7hgIausJZQJLnUq%2BDjVdrT8Fa%2BBNKd%2FGTDH2P%2FgbgZoTc4mMxvekCelMg1PYkRFc7EwLneoXhmlQWt7kJVMDxMYrVSr9erHxeSAFWgwzpeoiqEaR%2Frn8LuYicIehmJfa9sRrp%2FW18nsrC41UUPzwYuyj%2BAYiJlOIAIqkQOB3gZhpFGDiyljU9DFI%2BZ3A%2BAwq8LHPQpAEZEkBTMGra9nKMd70ngyEMQuSnYTRlENaq2ExfQIxAVZB1%2BFjq5uDzc3OAvgIFAwpOG2yiRSWUG3njFHzuDhZIK06DHMCgja%2FEa30tosWggLBmpHsb2zVefIKBGo2r20SBWzROrXPpFkCiTaedx6HeaRwIX1TCzizSBvt0DhqqJUwVTiMTps4sxr5kOJ5vxwDtfVguEiTQ0YhL1RyP35SZ1EFTdPD4%2BX9DX0TvR7DWY1cFPgasXB91HnU71t3WwAgWDjXQ90MYM0QvV6FuQEdRIu8dGhGQxT0%2BF2OFMgNBiNrYJxFYSJiDICzaGuPvuvDxIiQBrCZRZuyTqNJ72Mw0ISoafBWgVC5EsPhCCbLNIz3DSwX2FhkTBWkAaIPKNwkQkjwLohmG1sihYTCAnVbw5Y9BMmHWWR0AONGVARN7VFWPQxjwshHFHkPKSZ43yDP6ZdYJJoZq%2B%2FD9yyKHE07gLVktAqJALb%2BKfuHsGSNzufYmlB4zoFAxAeJFjymegEbNszAlctgiwqt34jSBvRSQj0YwuQVTFGJpQ0xIkWlZCpUZjP4uRZlUSDZiNbXspgh0t2a9%2B7FX4%2BMGjQvIfF%2B7dHrT6FNFiG0qLIGKUT4WCDFFr1sgDLLsGEmoupNAs6jYSia9UEc0jmkBg9jhMsq0JkvHNDW6zBRGPTgMDM7h9jrw1UTiK06p0ZApmbQOofZuRlMTEwhBjJcizxt5AGIdhImKzE7tx79iUkEMlH0KNIARE2wpTDV1twWBAgeDrlLMPU65HkPg7QNbDmBq6%2B%2BHK%2FffVecfuxR%2BMH3v4%2FHG9Kvg4kBzuVogoPLc%2FhYo8pztHM1HLN2%2FM3XqGwL39ZAXongJcUsTqjmEYRqTYQzDj6V2NAU2G7bPv7la59Bv99DW2wnOHvnW4%2FAd7%2FzLcw2fZi8R%2BsNnyJG3sBlBVJTIy9yeDJTtMiLKaw%2B4Vjs8ofLcME5Z8EFg4nJaWzwHqNgkIJDVVSomyGsOIAMd534LXk5heAdPnvtJ7H7rjvgiIMOwI8f3IDGVWhNRFb14b3D5669Gnu%2B8qV480H74yc%2Ff0JC4q25PedAoK0lXU71S2C0Hs4WmIvTOGnNmTjxpONRYQ4Xr12De79xFx5POYItUDB0yksMWotoLWJskDuLPBn4aJHKSfEH8tEjKFzCCIWEbAoE2nl68RHWMOVLm2swjBXS1O%2Fh76%2B9Cg9970u45JKL8bD%2FXaw5bQ3OXLUnjly5Px4blPCm0vyDJRwcjM3kaq33yCd6uPra6%2FGlW%2F4JTzzyM7xsxxyP%2Fc9%2F4ry1F%2BDIVUfjofXrUPSnEH0BkplzQIgNssKhCREmn8Aer9sXV37sEnz5xi%2FjhJV%2FjKMPXYGfPB4ww5C1zLHHXvvgiisvxy03fgknHvRaHHPICvzo4Y2orTqkW2tbACAYJJch%2BBZZaGFNhmpqGW6%2B9Q68%2F8OX4MJzz8Rl7zkJ93%2FzToz6y%2BBthVQ3cK5AzTyCpTAi3QQ40jlybAgVbKyxfbYOsd2INp%2BWCIDOHcM%2BDSmZBxyJs2kZZ5hJ1FM74fZbb8LdN7wPn7r6k%2FgFXobl%2Bx6AT3%2FoNKxauR%2F%2B42dzqBlxkI4BTEwvFcrPbSbUPmiHcNUULr3iOizfZ1dM21n8%2B%2F13Y%2FXqd2Aw8sgnC8yNPIxdApeVGA1m0JsoMKwHcEUFZJO49Wv34qijT8br%2FugPcNWFx%2BPYww7Ad3%2Feoq62EXtz29fuxlHHnIQ%2F3XVnfPKCU3DsoSvww0eHGNne1sKA3Oc5BwIpuw60vX2G9EjB49JLLsKyF78Ux5x8Nr51zx3463OOx%2F333IHZfCkGIUNso5gGqpRE8ikgBi8OWrR9zLltcdbpJ%2BOg3V6ENauPxYY0gRYVbKJJcBplwKOIQ03T2hIzvsSg2hHvOONtOP%2FEvfD2U0%2FGt37q8ZWvfBU%2FuvezOP%2FsM5AVv4PgepgZDdCbmsLcsIaho8bKZpbBmyj2%2BoIPXIljDn8dpjCLH9x3J457yymweYWQMxLIEENfHLy2GaDq5Ug2YVh7ZL1pzNRktGXYf49X4fpLTsVxB74JP9xQYp3PUfZybKyj7F%2B%2Bx2tw%2FSVn4S0r9sWPHmteAECg953lqNuEJhbY5RU74abrPorDjzoaD2ycwh1fuQWfeO9xuP%2FOf8ScW4LGTSBljMMBZ1q0bYNkM0xNTmJuZj0a9FBXL8GZp74Vh%2F%2FJdjjlbaswa5eiBSMKOolO4nMCoRcZ3qkztjHkiNU0UhrhkDftiquuuByj0MdHP3IpbrjmUpTMSPgeag9k20xi0LaSiTTGiaM69A1Q5Pj0jTfhttvvx0MP%2FBg7LfMYPPrfuPCcC3DwYUfi0brGoE2YqpZiNGyQlxna0CKEAEu%2Fxubyfht8H0cu3xPX%2FdUaHP361%2BJ7j%2BZoi2lkpkGwOdb7CRy639645rK1OGqv3fHTdUHqGltze84ZYZx%2Fb1KGbGI7XHfNJ7Duge9g7XvOx3Di5bj7jjvw8T9%2FC759z%2B2YMwqEaAo0vkGRJ2EDYws0bYDLHFI2ifVNH2efvhqHv3YHrD7uCNR2CRrTRwQzjbmkay3aDgjAnC%2Fk3jOtwelvPxHnvfNYrDl5NbJqe1z1sctxyxc%2Bhb88%2F73I0ySSK7AhDIEsh7Ml2pZZpoSy18NsXUtkE1DhzNNOwu9vn%2BNDF74XuQd4mK%2F6IshU0z%2Bho5tJ2FlkmeQlNrYBmFiG9WkJVu69Gz570Vtx3Io34fuP9zCyE4j1euT9KTyRluKAffbEdRefiT878I34ySMjSZ6N65BP7a9YCIAsCBCcyzBsA95w4CE4Z%2B27sHrVwVi%2FYQ5%2B8qW48%2FZbcdm5x%2BHb37wTM2YayfaQ6pEsYmMtPBNNUnTJcOxJp2Ht2jPE05fcA2YAVgKjwy2fvxnnfuCjCHkfbUv%2FIKCPERj%2F0QEcmgmkJS%2FBXbffjJuufDc%2Bf%2BNnsM68BMv3XY6%2F%2B8hanHPGqbjrn3%2BAhg4ri1wG8BLbayGM26a%2BBqk5MAnWSOJaG2K0f0CT2ZIJ6gpfEaUzYhpCNYmBXYLH0zSO2m8P3PAXb8YJK5fju48WmEMflWswShk2ZNvjwDfuhesvPhOrVuyDn%2F5iFo0pYZxDluVofUBghZa%2BF8Nc9l%2FoE6p934SMX9Vg8%2FTQWQAgMN5u0Zuaxvsu%2BhAOW3kA%2Bl0RZ0Zy6sBkeBSzDz%2BIfQ8%2FATMbG0w6oGka1AwLXQb4AJOXGNkKtY%2FiNP7tFR%2FGjks8Tj%2FxOKQWMNUUfj7wKKdfhLphro9VwjlEXyO6Slhjh513xxdu%2FDguPXsV7rv3HjzY7oBXv%2Fo1uPWaD%2BL8tWfhtnv%2BFUOGfhUdvBGKXk%2FyFMw5PLXH6OmXssttsOjEZFTO%2BKjE0E1hkG%2BLg1%2B%2FGz538Uk4%2FoA34N8e9ojVUniGm9USPB4msd8b9sJnLj4bhx%2BwN%2F7r0VlEV6JuWvgYUVU9WKeAsNbKMyoCOiBsykQ%2Bz4DAOL5h1s1aVGUBDDZKZj6ffjG%2BfvtXcel5J%2BP%2Be%2B%2FCTOqjDazOBZRliY1JHc2CyE9Aw4JNbxKzg4C%2FuexDeOX2CUcfsRK2XIbgKiC3GLRAjQlJEFVhgzBDnXLUdgJ2akfc%2FtUvon7wPrz5yMPQ9nfCRRddjFX77YaD938jHvjFDPLeFJrWSzdRYpk7BPERtnTTmmNEw3xxbzusr6NUTY%2FYf29c88F347Dle%2BPB9XN4fK6BK6YQmYW1JQ5ZsS%2Buev85OHLlCjzw8GOoA1CWFXyIkmwjC%2FA5CYRNjPB8B4IUnWgeGMpZZuKAum5RTu%2BAO267GRe951R88xtfR6q2hQ8W%2FTzDcFSjdbmkhSUYZMqWoM8rRFPh0g9eiJdvb3HKCcehMZOYHXmJ16V%2BUCxF8h5Zsx65MxLyPbHRA%2BU2qArgvjuux3Yv2g4BU%2FAeOHT5nnj8kYcQaWzmRuhPLkFICU1o4ZgMGKcutwANAgRrMDdqgWob3PgPX8Qur%2Fo9MCtQ%2BFrqD7Ee4F3nnod77vsePnXdDXjFLi%2BX1HUWWyCwG8pgzemn486v34WirNC0rZiFvKAPw16OJ3ddPm9Ng2GC31jMtS16pNvhEGXZw1xDJY6YdkP4ZoDWkvIKxNaLIEKWI8sLtDyeufxA2%2B3EVGQMDx2rhl77XmyO2mWI7FFotcLJYzJnMVd7FL0l8DFH8CNM9GrJSLa%2BgLMOORo4mxBpAng%2Bu5hiRJ478Nlpj5%2FNFrxHf3JawEoBpkDfwgO%2BkZA4k3KjkxxGm5ghjSgypplbZM6gaRtENsRUPX0W%2BiIxYTAcYWqKYNbGn82bX5%2F8%2FZkbtgXxEaLk6HN45t3ZZ8DKm08SDUibV7MBZabaPGoCJicm0fiA6BzqusFkWWJuOJSiD3P32sHEpA8z8BEFmcblmPEJedVHahpkVmv%2BZJKsKBGikZCuICVkLerRAKVjlYAgZV2DCeAEOraj2ku4x7qEMMIWewjquvGapPHEXsqYpE7BvEjZrzAaDJAL2AKQl5KvIJuF1su9fWD05IRJCUoKn9fr9foY1Q24tixc%2FUYAgcITt8WyCpfQth5Lp7eRAlRVOJQuYW7jLCak4AKM6hbVxCSGDVPE7CWOoq103phXEIFlTrSWVT3tRTSoGWHkBVLbAGKGrHxvGpZ%2FgR6pNAYMWa7OHIrAglQj3jirhLMzT2B6yRLJfg5HI%2FQmehg1ddc2t6WcQKA5EXSMHmWRs8YtVUbjkjBfChYTExOYnV0nwncml4Sa5zHS6eyRWOlijwQZhf5Sq211VVXB07790u355iwS2d7DEe0J4vkSyXToqImJTR4ECUvMISIrK9RtEBqVjZW4TNPUQbTfyT4KKc8LTdiQaRhuto1oTs5MYND7FEWJejBAvyxR%2BxYD7i8LONpaJh4zloRbVL0CbV2zoUyAy8ITaw7albClm%2FYcsJrMknc9HKFkYSl4tC0%2Fs7Sdoa5rTPacPD%2BQo25oTiYwGg1FWeqaJWkCljkVdRIJ3gEZRUrdv2x7ngFhPr4dN6Rou9q4S1davKX9S1vSmHwZt4BrDw9tPmsG2krmDVvc1JbyLytz0qDGuQJmEqXhpZt96GwnFYvHc5%2F2BrJlrOuW6uYM6Nhr4woLVdocKx3JMuPwbDZtZqXfIu0MXes8%2By%2F5%2FEyAcct4X9k%2FnuHQ55Hc%2FJOGbn69p9lyGCxArWHczq3WcjzooY4NBauvqjZu3AmkIJjvb5wHgk4%2Bsag07tzhohEYKkC2qmnHsXrSFKK2k4%2F7DKVtvAMCBSGMIANHbGTRNvRS2uI5n6Ad188WCCI2ArETJ1vwuWnLGgdtuu%2Fa3q09imZ%2BckvDls3blJ4eCF2Oq1vXZw7iBXAWVRAqaBWu9ikS%2FQqECO0uZvuXTkBJI2E3jCI5u04zusZXdi5JJo9NoppO1ntoFlBbxIRzNl1XNUsXWTS9G52TFjm6GGBXtLKIAgFSs1BG2PKpAm1rn9dN7ZNQwYwzlmy74%2FuOB182P1oY4v9kDP9%2FwW4Ogs3v80zgsDBA6AZcZKKp89IV5eMpIG1zlyZRaRSdH4hRbVRqlLkH7XPuTIQWp1hulv2J0TmBoaNnDCAVYPMaNe4V7JRP2IDt42JiOs0sgwpegfDsRs6eDIROJTpTMJ71VMMzhqpyp5pKHYvbJNhf00SNgTYW%2FJb0PC4IEDinoDTNvn6CX%2Fv550fAWD4eO2TSB6w2WeYcFAh6rDabUtA6baBOkjW1anuqdKQMOnqmZMz7dnmAzjZrU2hnMmTwReHFHkdu7KbmefOzkc%2FOR5C2tU22nnfg4M5mbfrSysdbarPrJoXh23aDupvnjJ7OMDzPgcB5rlxoUodgx23qpHM2942neaSPWDV6PJAi8wPa%2Fq7dwCponZxiFzIHXti3oECQbuFuqGVsn8cTT2Nq1u5lHYixUQEXZfLIwAYOuGjPow7JPhsgaAFKY4fxUGzXZ8EklsxCapyiTEZHdewsd1Nhwgrz5unpNPx5DQQRUNK5AgemVjm3SFPBDJr%2BlelfE%2BC6uQMCR19fowNtG%2B%2FawplMkhlCmhKdJ0jo6YR1B4TxfIAUAzuvQYGg19SZRW1%2FV%2BoZ6f0STQ6vw2rWuL38mVjYzY9VEUuqmaNxXaSjlcqsc3w1omGbPSEydm9ZUtfJ6PkZSjU1T%2BMjPIUynu74X3a1BTINStMy%2BSzEyFiZjKBhHlvMxmVchkrzjNCNyHWzj2oalEIl1pApYoJEJ4wS2O2rCyfL3zl5m%2Bfix0BQd1GBQIDKZQ19DYMkQOB1SNVbXnkU69bpuiTBZExfTSKjHTrJ8rcbzS%2Bil2Ed0f%2FxfmG4JwNBdOFXYOGpjmKH%2F2cc%2Fv4vsRsv5%2Bl%2BoyoAAAAASUVORK5CYII%3D&culture=en-US






Response:
{
	"Html":"<div><span class=\"math-inline \">\n<asciimath style=\"display: none;\">4+8**11</asciimath></span></div>\n",
	"IsError":false,
	"Confidence":1,
	"Message":"Success."
}
		 */
	}

	public static void Editor() {
		/*
Request URL: https://www.mathway.com/chat/editor
Request Method: POST
Status Code: 200

accept: application/json, text/javascript, * / *; q=0.01
accept-encoding: gzip, deflate, br
accept-language: en-US,en;q=0.9
content-length: 1562
content-type: application/json
cookie: Mathway.LastSubject=Algebra; Mathway.IncomingCulture=en-US; Mathway.Culture=en-US; usprivacy=1YNY; _ga=GA1.2.34571938.1649718565; Mathway.AnonUserId=794552748; sbm_country=US; Mathway.StaticAdsTest={%22testId%22:33%2C%22testVariantId%22:154}; Mathway.AnonymousPurchasing={%22testId%22:29%2C%22testVariantId%22:133}; Mathway.CreditPackPurchasingTestVariant={%22testId%22:18%2C%22testVariantId%22:129}; Mathway.UpgradeGuaranteeTestVariant={%22testId%22:22%2C%22testVariantId%22:121}; Mathway.QuarterlyTestVariant={%22testId%22:12%2C%22testVariantId%22:120}; Mathway.OneOffPurchaseTestVariant={%22testId%22:13%2C%22testVariantId%22:101}; Mathway.PlancodeTestVariant={%22testId%22:1%2C%22testVariantId%22:116}; _pbjs_userid_consent_data=3524755945110770; _pubcid=9d89a509-c582-4620-87e8-5be0ff34ef70; __gads=ID=ccfac60a85addd8b:T=1649718566:S=ALNI_MYTvTI1kaEsGjFwnBGbvfs-20XoqA; _lr_env_src_ats=false; pbjs-unifiedid=%7B%22TDID%22%3A%220ce9c866-8a89-4a02-b328-63dff6a864f8%22%2C%22TDID_LOOKUP%22%3A%22TRUE%22%2C%22TDID_CREATED_AT%22%3A%222022-03-11T23%3A09%3A27%22%7D; _gcl_au=1.1.79878581.1649718576; _fbp=fb.1.1649718575695.1766408596; _scid=16e7b9fa-3428-40f4-838e-528fa6420950; al_cell=edge-34-test; Mathway.Location=US; Mathway.GDPR=1; CVID=df335246-0984-4f9c-958e-1f2f9a6747f3; local_fallback_mcid=05457024589928240433100171871469060092; mcid=05457024589928240433100171871469060092; s_ecid=MCMID|05457024589928240433100171871469060092; _gid=GA1.2.1732146773.1650947382; apay-session-set=5dLexyaZfliFPaGVcxDD9C%2FqoRSXxez2bP%2FBwE%2B%2BTt2LzobfDCPWcIAWJjxpYjw%3D; language=en_US; amazon-pay-connectedAuth=connectedAuth_general; __gpi=UID=0000048cbcf64cc5:T=1650019843:RT=1650947381:S=ALNI_Mbsn2y9XzwY7tbpYSmYDRGwY86Q6Q; idl_env=AmnVSCswCnfTWqRzC6Fh1hnHYTD26C6SgyGDXxtCUPxl90zGpAzxw_Pc8LIJu39BAWj1kYgb0irb0yanZjAobZjJTWYvSb7lmBbb; IR_gbd=mathway.com; _sctr=1|1650862800000; CSID=1650959375972; OptanonConsent=isIABGlobal=false&datestamp=Tue+Apr+26+2022+02%3A50%3A58+GMT-0500+(Central+Daylight+Time)&version=6.13.0&hosts=&consentId=129be9da-4d46-4a10-800d-2d337bc235a2&interactionCount=1&landingPath=NotLandingPage&groups=snc%3A1%2Cfnc%3A1%2Cprf%3A1%2CSPD_BG%3A1%2Ctrg%3A1%2Cgoog%3A1&AwaitingReconsent=false; sbm_a_b_test=34-test; _awl=2.1650959458.0.5-fce18d318e7249707f84bed1f5996cdd-6763652d75732d6561737431-0; IR_14422=1650959460074%7C0%7C1650959460074%7C%7C; _gat=1
origin: https://www.mathway.com
referer: https://www.mathway.com/Algebra
sec-ch-ua: " Not A;Brand";v="99", "Chromium";v="100", "Google Chrome";v="100"
sec-ch-ua-mobile: ?0
sec-ch-ua-platform: "Windows"
sec-fetch-dest: empty
sec-fetch-mode: cors
sec-fetch-site: same-origin
user-agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/100.0.4896.127 Safari/537.36
x-requested-with: XMLHttpRequest

{
	"state":"GAEivwQKAzMrMxgCIiYKA0FkZBBbIg0KCHBvc2l0aW9uEgExIg4KCXRvcGljVGllchIBMziTyJ6TBkKABAEBAQAAAAEAAAEBAQABAAAAAAEBAAEAAAABAAEAAAAAAQABAQEAAAEBAAEBAAEAAAEBAQABAAAAAAABAAABAQAAAAEAAQAAAAAAAQABAAABAAEBAAEAAQEAAAAAAQAAAAAAAQEAAQEBAAEAAAEAAAABAAEAAQABAQEAAQEBAQAAAQAAAAAAAAAAAQAAAQABAQEBAAAAAQABAQABAAEAAQABAAEAAAAAAQEBAQEAAQEAAAABAAEBAQEBAAEAAQAAAQABAAABAAABAQAAAAEAAQEBAAAAAAEBAAABAQABAQAAAAAAAAEAAQAAAQEBAQEBAQEBAQEBAAAAAAABAQAAAQEAAAABAQEAAQEBAAABAQEAAAAAAQEAAQEBAAABAQEBAAEAAAAAAAAAAQABAAAAAQABAAABAAABAQAAAAAAAAABAQEBAQABAQEBAQABAAEAAQABAQEAAAEBAQABAAEAAAABAAAAAQEAAQEBAAEBAQEAAAEAAQEAAAEAAAABAQEAAQEBAAAAAQEAAAEBAAEBAAAAAAEAAAEAAQEAAQEBAQAAAQAAAQEAAQEAAAEAAQEBAAAAAQEBAAEBAAABAAABAQEAAQAAAAEBAQEBAQABAAEBAAEAAAABAAAAAAABAAEAAQABAAEBAQEBAAEBAQABAAEAAAABAQEBAAEAAQEAAAEBSHVVm2dbP2oZChVDaGF0X01lc3NhZ2VfUGh5c2ljczEQAXilAw==",
	"metadata":{
		"anonUserId":794552748,
		"userId":0,
		"UserRoles":0,
		"deviceId":"web;large;landscape;1",
		"acceptLanguages":"en-US,en;q=0.9",
		"route":"en",
		"version":"3.1.7",
		"affiliateId":"",
		"screen":{
			"width":1038,
			"height":929,
			"landscape":true,
			"density":1
		},
		"isAndroidWeb":false,
		"isiOSWeb":false,
		"splitTests":{
			"12":"off",
			"13":"off",
			"18":"off",
			"22":"off",
			"29":"off",
			"33":"p5-left-ads-2"
		}
	},
	"subject":"Algebra",
	"asciiMath":"4+8**11",
	"mathML":"<div class=\"editorLine\"><script type=\"math/mml\"><math><mrow><mn>4</mn><mo>+</mo><mn>8</mn><mo>&#x22c5;</mo><mo>&#x22c5;</mo><mn>11</mn></mrow></math></script></div>",
	"allTopics":false,
	"roomId":null,
	"topicStartIndex":0,
	"topicCount":0,
	"isOCR":true,
	"asciiEdited":false,
	"historyClick":false,
	"isSpeech":false
}




Response:

Success:

{
   "type":"messagesResponse",
   "messages":[
      {
         "action":{
            "method":"OpenViewSteps",
            "params":{
               "asciiMath":"4+8*11",
               "solutionId":"13209131568",
               "topicId":395,
               "topicText":"Evaluate",
               "customData":{

               },
               "freeTrialToken":"Pk6v7uSwwrPJqPOyerE2FCbA55GY+6nRrI4tvBkt7Ek=",
               "isTutor":false,
               "surveyID":0
            }
         },
         "content":"<div class=\"Explanation\">Simplify the <span class='GlossaryTerm' index='192'>expression</span>.</div><div class=\"Explanation\"><script type=\"math/mml\"><math><mstyle displaystyle=\"true\"><mn>92</mn></mstyle></math></script></div>",
         "callout":"ViewTrialSteps",
         "genre":"mathway",
         "timestamp":1650961716,
         "typeset":true,
         "isTutor":false,
         "showGraph":false,
         "testContext":3,
         "save":true
      },
      {
         "content":"<div class=\"Explanation\">Having trouble solving Physics equations? Doesn’t matter. We can help. Try our new Physics subject!</div>",
         "genre":"message",
         "timestamp":1650961716,
         "typeset":false,
         "isTutor":false,
         "showGraph":false,
         "testContext":0,
         "save":false
      },
      {
         "action":{
            "method":"InsertIntoEditor",
            "params":{
               "asciiMath":"4+8*11",
               "moreTopics":"y",
               "isTutor":false,
               "surveyID":0
            }
         },
         "content":"Not the answer you were looking for?",
         "callout":"AutoMoreTopics",
         "genre":"autoresolve",
         "timestamp":1650961716,
         "typeset":false,
         "isTutor":false,
         "showGraph":false,
         "testContext":11,
         "save":true
      }
   ],
   "state":"GAEirQQKBjQrOCoxMRgCIg0KCEV2YWx1YXRlEIsDMAE4tNqekwZCgAQAAAEAAAABAQEAAQAAAQEAAAAAAAAAAAABAQABAAAAAQEAAAEAAAABAQABAAABAQEBAQEBAQAAAAAAAQABAQEAAAAAAAEAAQAAAQEBAQABAQEBAAABAAABAAEAAQEBAQEAAQABAAAAAAABAQEBAAAAAQAAAAEAAQABAAEBAQEAAAEAAAABAAEAAAABAQEBAQEAAQAAAAEBAQEAAAABAAEAAQABAAEBAAEBAQABAAABAAEAAAABAAEAAQEAAAEAAAEAAAAAAQEAAQABAAAAAAEBAQAAAAABAQAAAQAAAQEBAAABAAABAQAAAAEBAQEBAQABAAEBAQEBAAAAAAEAAQEBAQEAAQEBAQEAAQEAAQEBAAAAAAEBAQEBAQAAAAEBAQABAAAAAAAAAAAAAQABAAAAAQAAAQEBAQEAAAAAAAABAQEBAAEAAQEBAQEBAAEBAAAAAQEAAAABAQEAAQABAAAAAAAAAAABAQEBAAEBAAEAAQAAAQEAAAEBAAEAAQEAAAEBAQAAAAEBAAEBAAABAQAAAAABAQABAAEBAAEBAQEBAAEAAAEBAAABAQABAAEAAQEAAAEBAQEBAAABAQAAAAEAAAABAQEBAQEBAQEBAQEBAQAAAAABAQAAAAEAAAEAAQEAAQEBAQEBAAABAQABAQAAAQABAQEBAQABAAAAAAEBAUh1VViYOD9YATgBQAFqGQoVQ2hhdF9NZXNzYWdlX1BoeXNpY3MxEAF4Lw==",
   "moreTopics":false,
   "nextIndex":0,
   "context":"NoError",
   "showInterstitial":false,
   "log":[
      {
         "SessionID":{
            "SessionStart":"\/Date(1650961716803)\/",
            "SessionID":199364803,
            "AnonUserID":801911209
         },
         "StartTime":"\/Date(1650961716803)\/",
         "EndTime":"\/Date(1650961716897)\/",
         "Server":"ChatBot_IN_0",
         "Endpoint":"Chatbot.Editor",
         "Type":"Receive"
      },
      {
         "SessionID":{
            "SessionStart":"\/Date(1650961716803)\/",
            "SessionID":199364803,
            "AnonUserID":801911209
         },
         "StartTime":"\/Date(1650961716803)\/",
         "EndTime":"\/Date(1650961716850)\/",
         "Server":"ChatBot_IN_0",
         "Endpoint":"Topics.GetTopics",
         "Type":"Request"
      },
      {
         "SessionID":{
            "SessionStart":"\/Date(1650961716803)\/",
            "SessionID":199364803,
            "AnonUserID":801911209
         },
         "StartTime":"\/Date(1650961716794)\/",
         "EndTime":"\/Date(1650961716840)\/",
         "Server":"Topic.Service_IN_5",
         "Endpoint":"Topics.GetTopics",
         "Type":"Receive"
      },
      {
         "SessionID":{
            "SessionStart":"\/Date(1650961716803)\/",
            "SessionID":199364803,
            "AnonUserID":801911209
         },
         "StartTime":"\/Date(1650961716850)\/",
         "EndTime":"\/Date(1650961716897)\/",
         "Server":"ChatBot_IN_0",
         "Endpoint":"Solver.Solve",
         "Type":"Request"
      },
      {
         "SessionID":{
            "SessionStart":"\/Date(1650961716803)\/",
            "SessionID":199364803,
            "AnonUserID":801911209
         },
         "StartTime":"\/Date(1650961716866)\/",
         "EndTime":"\/Date(1650961716866)\/",
         "Server":"SolverQueueWorker_IN_1_2207",
         "Endpoint":"Solver.Solve",
         "Type":"Receive"
      }
   ],
   "ErrorType":"NoError",
   "RequestFollowUp":false,
   "problemCount":1
}



Error:

{
	"type":"messagesResponse",
	"messages": [
		{
			"content":"<div class=\"Explanation\">I don't recognize this problem, please make sure the input is complete.</div>",
			"genre":"mathway",
			"timestamp":1650959672,
			"typeset":false,
			"isTutor":false,
			"showGraph":false,
			"testContext":12,
			"save":true
		}
	],
	"state":"GAEivwQKAzMrMxgCIiYKA0FkZBBbIg0KCHBvc2l0aW9uEgExIg4KCXRvcGljVGllchIBMziTyJ6TBkKABAEBAQAAAAEAAAEBAQABAAAAAAEBAAEAAAABAAEAAAAAAQABAQEAAAEBAAEBAAEAAAEBAQABAAAAAAABAAABAQAAAAEAAQAAAAAAAQABAAABAAEBAAEAAQEAAAAAAQAAAAAAAQEAAQEBAAEAAAEAAAABAAEAAQABAQEAAQEBAQAAAQAAAAAAAAAAAQAAAQABAQEBAAAAAQABAQABAAEAAQABAAEAAAAAAQEBAQEAAQEAAAABAAEBAQEBAAEAAQAAAQABAAABAAABAQAAAAEAAQEBAAAAAAEBAAABAQABAQAAAAAAAAEAAQAAAQEBAQEBAQEBAQEBAAAAAAABAQAAAQEAAAABAQEAAQEBAAABAQEAAAAAAQEAAQEBAAABAQEBAAEAAAAAAAAAAQABAAAAAQABAAABAAABAQAAAAAAAAABAQEBAQABAQEBAQABAAEAAQABAQEAAAEBAQABAAEAAAABAAAAAQEAAQEBAAEBAQEAAAEAAQEAAAEAAAABAQEAAQEBAAAAAQEAAAEBAAEBAAAAAAEAAAEAAQEAAQEBAQAAAQAAAQEAAQEAAAEAAQEBAAAAAQEBAAEBAAABAAABAQEAAQAAAAEBAQEBAQABAAEBAAEAAAABAAAAAAABAAEAAQABAAEBAQEBAAEBAQABAAEAAAABAQEBAAEAAQEAAAEBSHVVm2dbPyIjCgc0KzgqKjExGAIiADi4yp6TBkj///////////8BVQAAgL84AWoZChVDaGF0X01lc3NhZ2VfUGh5c2ljczEQAXgu",
	"moreTopics":false,
	"nextIndex":0,
	"context":"NoError",
	"showInterstitial":false,
	"log": [
		{
			"SessionID":{
				"SessionStart":"\/Date(1650959672481)\/",
				"SessionID":1467605579,
				"AnonUserID":794552748
			},
			"StartTime":"\/Date(1650959672481)\/",
			"EndTime":"\/Date(1650959672528)\/",
			"Server":"ChatBot_IN_2",
			"Endpoint":"Chatbot.Editor",
			"Type":"Receive"
			},
			{
				"SessionID": {
					"SessionStart":"\/Date(1650959672481)\/",
					"SessionID":1467605579,
					"AnonUserID":794552748
				},
				"StartTime":"\/Date(1650959672481)\/",
				"EndTime":"\/Date(1650959672528)\/",
				"Server":"ChatBot_IN_2",
				"Endpoint":"Topics.GetTopics",
				"Type":"Request"
			},
			{
				"SessionID":{
					"SessionStart":"\/Date(1650959672481)\/",
					"SessionID":1467605579,
					"AnonUserID":794552748
				},
				"StartTime":"\/Date(1650959672496)\/",
				"EndTime":"\/Date(1650959672528)\/",
				"Server":"Topic.Service_IN_0",
				"Endpoint":"Topics.GetTopics",
				"Type":"Receive"
			}
		],
		"ErrorType":"NoError",
		"RequestFollowUp":false,
		"problemCount":0
}
		 */
	}

}
