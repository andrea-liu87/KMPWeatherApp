import SwiftUI
import shared

struct ContentView: View {
    @State private var place = ""
    @State private var temp = ""
    @State private var description = ""
    
    var api = WeatherAPI();
    
    var body: some View {
        Color.black
            .ignoresSafeArea()
            .overlay(
                VStack {
                    Text(place).foregroundColor(Color.white)
                        .font(.largeTitle)
                    Text("\(temp)° C")
                        .foregroundColor(Color.white)
                        .font(.system(size: 96))
                    Text(description)
                        .foregroundColor(Color.white)
                        .font(.largeTitle)
                })
            .onAppear(perform: retrieveData)
    }
    
    func retrieveData() {
        api.getWeatherAPIData (successFunction: { it in
            place = it.name ?? "";
            guard let tempDouble = it.main.temp as? Double
             else {
                temp = "error"
                return
            }
            if (temp != "error") {
                temp = String(format: "%.0f", tempDouble - 273.0)}
            guard let weather : Weather = it.weather[0] as? Weather else {
                return
            }
            description = weather.description_ ?? ""
        },
                               failureFunction: { error in
            place = error
        })
    }
    
    struct ContentView_Previews: PreviewProvider {
        static var previews: some View {
            ContentView()
        }
    }
}
